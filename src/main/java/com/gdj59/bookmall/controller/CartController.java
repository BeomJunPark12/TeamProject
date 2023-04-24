package com.gdj59.bookmall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.endpoint.AbstractMessageEndpointFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.service.UserServiceImpl;

@RequestMapping("/cart/*")
@Controller
public class CartController {
	@Autowired
	public UserServiceImpl userServiceImpl;
	@PostMapping("/addCart.do")
	public ResponseEntity<Integer> addCart(@RequestParam("b_name") String b_name, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session != null && (boolean) session.getAttribute("isLogOn") == true) {// 로그인 되어있는 상태
			BookVO book = userServiceImpl.searchBookCnt(b_name);// 책 재고 개수 //1.책 재고 조회후
			if (book.getB_stock() > 0) { // 1-1 책 재고 있는 경우
				UserVO userVO = (UserVO) session.getAttribute("userVO");
				String user_id = userVO.getUser_id();
				int b_num = book.getB_num();
				try {// 장바구니에 넣을 책이 현재 장바구니에 있는경우
					int cnt = userServiceImpl.cartBookCnt(user_id, b_num);// 조회함수만들고 ordercnt값 조회 user_id and b_num이
					userServiceImpl.cartUpdate(user_id, b_num);
				} catch (Exception e) {// 장바구니에 넣을 책이 현재 장바구니에 없는 경우
					userServiceImpl.cartInsert(user_id, book);
				}
				return ResponseEntity.ok(1);
			} else {// 1-2 책 재고 없는
				return ResponseEntity.ok(2);
			}
		} else {// 로그인 안한상태
			return ResponseEntity.ok(0);
		}
	}
}
