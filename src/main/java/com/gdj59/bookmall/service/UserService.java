package com.gdj59.bookmall.service;

import java.util.ArrayList;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.UserVO;

public interface UserService {
	public void registerInfo(UserVO userVO);
	public int overLap(String user_id);
	public UserVO selectOne(UserVO userVO); 
	public void modInfo(UserVO userVO);
	public void deleteMember(String user_id);
	public BookVO searchBookCnt(String b_name);
	//public void addCart(String user_id,BookVO book);
	public ArrayList<CartVO> cartList(String user_id);
	public void modifyCart(CartVO cartVO);
	public CartVO selectCart(CartVO cartVO);
	public void 완전삭제(int cart_id);
	public int cartBookCnt(String user_id,int b_num);
	public void cartUpdate(String user_id,int b_num);
	public void cartInsert(String user_id,BookVO book);
	public void updateUserGrade(String user_grade, String user_id);
}
