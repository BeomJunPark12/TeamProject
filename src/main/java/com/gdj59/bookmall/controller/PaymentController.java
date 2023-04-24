package com.gdj59.bookmall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.IamportClient;
import com.gdj59.bookmall.beans.PaymentVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.service.UserService;
import com.gdj59.bookmall.service.paymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private IamportClient api;
	private PaymentVO paymentVO;

	// api 생성
	public PaymentController() {
		this.api = new IamportClient("3632840470287374",
				"RUbU2UQtqtP627t6peVjLTRaVV6zWUQOG4lLXHM8eUL9GNU78E9lay0NOIfP8hoRwZVdlkDKm6bCXATB");
	}

	@Autowired
	UserService userService;

	@Autowired
	paymentService paymentService;

	@GetMapping("/form")
	public String payForm(Model m, HttpSession session) {
		// 1. 로그인한 사용자의 아이디를 가져온다.
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		userService.selectOne(userVO);

		// 2. 해당 사용자의 장바구니에 담긴 상품들을 조회한다.
//	    ArrayList<CartVO> cartList = userService.cartList(user_id);
		ArrayList<CartVO> cartList = (ArrayList<CartVO>) session.getAttribute("list");

		// 3. 조회한 상품 정보를 모델에 담는다.
		m.addAttribute("cartList", cartList);
		m.addAttribute("userVO", userVO);
		
		// 총 결제 금액 계산, 총 상품 이름 계산
		String[] totalName = new String[cartList.size()];
		int totalPrice = 0;

		for (int i = 0; i < cartList.size(); i++) {
			CartVO cartVO = cartList.get(i);
			totalPrice = totalPrice + cartVO.getB_price();
			totalName[i] = cartVO.getB_name();
		}
		
		m.addAttribute("totalName", Arrays.toString(totalName));
		
		if("vip".equals(userVO.getUser_grade())) {
			int discount = 10;
			int discountedPrice = totalPrice * (100 - discount) / 100;
			
			m.addAttribute("discountedPrice", discountedPrice);
			m.addAttribute("discount", discount);
		} else {
			m.addAttribute("totalPrice", totalPrice);
		}

		// 4. 결제 페이지로 이동한다.
		return "payForm/payForm";
	}

	@PostMapping("/success/{imp_uid}")
	@ResponseBody
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session,
			@PathVariable(value = "imp_uid") String imp_uid) throws IamportResponseException, IOException {
		System.out.println("도착");
		System.out.println(imp_uid);

		// Iamport API로부터 결제 정보 가져오기
		IamportResponse<Payment> response = api.paymentByImpUid(imp_uid);
		Payment payment = response.getResponse();

		// 결제 정보 DB에 저장하기
		// 1. 결제 테이블에 insert
		Date pm_date = payment.getPaidAt();	// 결제 날짜
		String pm_id = payment.getBuyerName(); // 결제 아이디
		int pm_b_price = payment.getAmount().intValue();	// 결제 가격
		String pm_b_name = payment.getName(); // 결제 상품이름

		PaymentVO paymentVO = new PaymentVO(pm_date, pm_id, pm_b_price, pm_b_name);
		System.out.println("결제 완료" + paymentVO.toString());
		
		try {
			// 1. 결제 정보 insert
			paymentService.savePayment(paymentVO);

			// 2. 유저 테이블에 해당 회원 누적금액 상승
			paymentService.saveMaxPrice(pm_b_price, pm_id);

			// 3. 책의 누적구매 횟수, 재고 차감 update
			pm_b_name = pm_b_name.replace("[", "");
			pm_b_name = pm_b_name.replace("]", "");
			StringTokenizer st = new StringTokenizer(pm_b_name, ",");
			String[] arr = new String[st.countTokens()];
			int i = 0;

			while (st.hasMoreElements()) {
				arr[i] = st.nextToken().trim();
				i++;
			}

			for (String b_name : arr) {
				ArrayList<CartVO> cartList = (ArrayList<CartVO>) session.getAttribute("list");
				
				int b_stock = 0;
				
				for (CartVO cart : cartList) {
					if (cart.getB_name().equals(b_name)) {
						b_stock = cart.getOrdercnt();
						break;
					}
				}
				// 책 재고 감소, 책 누적구매 횟수 증가
				paymentService.UpdateBook(b_stock, b_name);
			}

			// 누적금액 100,000원 이상이면 회원등급 vip로 업데이트
			UserVO userVO = (UserVO) session.getAttribute("userVO");

			int maxPrice = Integer.parseInt(userVO.getUser_maxPrice());
			String grade = userVO.getUser_grade();

			if (maxPrice >= 100000 && grade.equals("basic"))
				userService.updateUserGrade("vip", userVO.getUser_id());
			
			// 결제 후 장바구니 삭제
			String user_id = userVO.getUser_id();
			paymentService.removeCart(user_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return api.paymentByImpUid(imp_uid);
	}

}
