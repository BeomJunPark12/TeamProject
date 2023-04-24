package com.gdj59.bookmall.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.UserVO;
import com.gdj59.bookmall.mapper.userMapper;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private userMapper userMapper;

	@Override
	public void registerInfo(UserVO userVO) {
		userMapper.registerInfo(userVO);
	}

	@Override
	public int overLap(String user_id) {
		return userMapper.overLap(user_id);
	}

	@Override
	public UserVO selectOne(UserVO userVO) {
		// TODO Auto-generated method stub
		return  userMapper.selectOne(userVO);
	}

	@Override
	public void modInfo(UserVO userVO) {
		 userMapper.modInfo(userVO);		
	}

	@Override
	public void deleteMember(String user_id) {
		userMapper.deleteMember(user_id );
		
	}

	@Override
	public BookVO searchBookCnt(String b_name) {
		return userMapper.searchBookCnt(b_name);
		
	}


	@Override
	public ArrayList<CartVO> cartList(String user_id) {
		// TODO Auto-generated method stub
		return userMapper.cartList(user_id);
	}

	@Override
	public void modifyCart(CartVO cartVO) {
		// TODO Auto-generated method stub
		userMapper.modifyCart(cartVO);
	}

	@Override
	public CartVO selectCart(CartVO cartVO) {
		// TODO Auto-generated method stub
		return userMapper.selectCart(cartVO);	
		}

	@Override
	public void 완전삭제(int cart_id) {
		// TODO Auto-generated method stub
		userMapper.완전삭제(cart_id);
	}

	@Override
	public int cartBookCnt(String user_id,int b_num) {
		// TODO Auto-generated method stub
		return userMapper.cartBookCnt(user_id,b_num);
	}

	@Override
	public void cartUpdate(String user_id, int b_num) {
		userMapper.cartUpdate(user_id,b_num);				
	}

	@Override
	public void cartInsert(String user_id, BookVO book) {
		// TODO Auto-generated method stub
		userMapper.cartInsert(user_id,book);
	}

	@Override
	public void updateGrade(String user_grade, String user_id) {
		userMapper.updateGrade(user_grade, user_id);
	}

	


	

}
