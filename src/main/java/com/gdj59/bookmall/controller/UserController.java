package com.gdj59.bookmall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.IamportClient;
import com.gdj59.bookmall.beans.PaymentVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.dto.JoinDto;
import com.gdj59.bookmall.dto.UserVODto;
import com.gdj59.bookmall.service.UserServiceImpl;
import com.gdj59.bookmall.service.paymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RequestMapping("/member/*")
@Controller
public class UserController {

	@Autowired
	public UserServiceImpl userServiceImpl;
	
	@PostMapping("/login_proc.do")
	public String loinProc(UserVODto userVODto, HttpServletRequest request, HttpServletResponse response) {

		String nextPage;
		UserVO userVO = UserVO.builder()
				.user_id(userVODto.getUser_id())
				.user_pw(userVODto.getUser_pw())
				.build();
//			====================================================
		String user_grade = userServiceImpl.selectOne(userVO).getUser_grade();
//			====================================================
		userVO = userServiceImpl.selectOne(userVO);// 로그인하면서 정보
		if (userVO != null && userVO.getUser_id() != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("userVO", userVO);
//			====================================================
			session.setAttribute("grade", user_grade);
//			====================================================
			nextPage = "main";
		} else {
			nextPage = "login";
		}
		return nextPage;
	}

	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "main";
	}

	@GetMapping("/myPage.do")
	public String myPage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		boolean isLogOn = false;
		if (session != null) {
			isLogOn = (boolean) session.getAttribute("isLogOn");
			if (isLogOn == true) {
				UserVO userVO = (UserVO) session.getAttribute("userVO");
				String user_id = userVO.getUser_id();

				int b_price = 0;
				ArrayList<CartVO> list = userServiceImpl.cartList(user_id);
				for (int i = 0; i < list.size(); i++) {
					int ordercnt = list.get(i).getOrdercnt();
					b_price = list.get(i).getB_price() * ordercnt;
					list.get(i).setB_price(b_price);
				}
				session.setAttribute("list", list);
				return "user/myPage";
			} else {
				return "user/login";
			}
		} else {
			return "user/login";
		}
	}

	@GetMapping("/loginPage.do")
	public String login() {
		return "user/login";
	}

	@GetMapping("/join.do")
	public String join() {
		return "user/join";
	}

	@GetMapping("/main.do")
	public String aa() {
		return "user/main";
	}

	@GetMapping("/buy.do") // 미완성
	public String buy(HttpServletRequest request) {
		return "user/buy";
	}

	@GetMapping("/deleteMember.do")
	public String deleteMember(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		userServiceImpl.deleteMember(user_id);
		return "user/login";
	}

	@PostMapping("/join_proc.do")
	public String joinProc(JoinDto joinDto) {
		String user_address = joinDto.getZipcode();
		user_address += joinDto.getRoadAddress();
		user_address +=joinDto.getJibunAddress();
		user_address += joinDto.getNamujiAddress();
		String user_phone = "010";
		user_phone += joinDto.getHp2();
		user_phone += joinDto.getHp3();
		UserVO userVO = UserVO.builder()
				.user_id(joinDto.getUser_id())
				.user_pw(joinDto.getUser_pw())
				.user_address(user_address)
				.user_phone(user_phone)
				.user_maxPrice("0")
				.user_grade(null)
				.build();

		userServiceImpl.registerInfo(userVO);
		return "user/login";
	}

	@PostMapping("/overLap.do")
	public void overLap(@RequestParam("user_id") String user_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = userServiceImpl.overLap(user_id);
		PrintWriter out = response.getWriter();
		out.print(result + "");
		out.flush();
		out.close();
	}
	



}
