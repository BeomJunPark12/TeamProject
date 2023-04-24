package com.gdj59.bookmall.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.dao.UserDaoImpl;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Override  
	public void registerInfo(UserVO userVO) {
	userDaoImpl.registerInfo(userVO);
	}
	@Override
	public int overLap(String user_id) {
		return userDaoImpl.overLap(user_id);
	}
	@Override
	public UserVO selectOne(UserVO userVO) {
		return userDaoImpl.selectOne(userVO);
	}
	@Override
	public void modInfo(UserVO userVO) {
		userDaoImpl.modInfo(userVO);
	}
	@Override
	public void deleteMember(String user_id) {
		userDaoImpl.deleteMember(user_id);
	}
	@Override 
	public BookVO searchBookCnt(String b_name) {
		return userDaoImpl.searchBookCnt(b_name);
	}

	@Override
	public ArrayList<CartVO> cartList(String user_id) {
		// TODO Auto-generated method stub
		return userDaoImpl.cartList(user_id);
	}
	@Override
	public void modifyCart(CartVO cartVO) {
		// TODO Auto-generated method stub
		userDaoImpl.modifyCart(cartVO);
	}
	@Override
	public CartVO selectCart(CartVO cartVO) {
		// TODO Auto-generated method stub
		return userDaoImpl.selectCart(cartVO);
	}
	@Override
	public void 완전삭제(int cart_id) {
		// TODO Auto-generated method stub
		userDaoImpl.완전삭제(cart_id);
	}
	@Override
	public int cartBookCnt(String user_id, int b_num) {
		return userDaoImpl.cartBookCnt(user_id,b_num);
	}
	@Override
	public void cartUpdate(String user_id, int b_num) {
		userDaoImpl.cartUpdate(user_id,b_num);		
	}
	@Override
	public void cartInsert(String user_id, BookVO book) {
		userDaoImpl.cartInsert(user_id,book);
		
	}
	@Override
	public void updateUserGrade(String user_grade, String user_id) {
		userDaoImpl.updateGrade(user_grade, user_id);
	}
	

}
