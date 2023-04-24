package com.gdj59.bookmall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.service.UserServiceImpl;

@RestController
@RequestMapping("/api/*")
public class ApiController {

	@Autowired
	public UserServiceImpl userServiceImpl;

	@PostMapping(value = "/완전삭제.do")
	public void updateMember(@RequestParam int cart_id) {
		userServiceImpl.완전삭제(cart_id);
	}

	@PostMapping(value = "/updateMember.do", consumes = "application/json")
	public void updateMember(@RequestBody UserVO userVO, HttpServletRequest request) {
		userServiceImpl.modInfo(userVO);
		userVO = userServiceImpl.selectOne(userVO);
		HttpSession session = request.getSession(false);
		session.setAttribute("userVO", userVO);
	}

	@PostMapping(value = "/modifyCart.do", consumes = "application/json")
	public ResponseEntity<CartVO> modifyCart(@RequestBody CartVO cartVO) {
		userServiceImpl.modifyCart(cartVO);
		cartVO = userServiceImpl.selectCart(cartVO);
		return ResponseEntity.ok(cartVO);
	}

}