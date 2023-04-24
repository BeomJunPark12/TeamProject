package com.gdj59.bookmall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdj59.bookmall.beans.PaymentVO;
import com.gdj59.bookmall.mapper.PaymentMapper;

@Repository
public class PaymentDaoImpl implements  PaymentDao {
	
	@Autowired
	PaymentMapper paymentMapper;

	@Override
	public PaymentVO selectPayment(Integer pm_num) throws Exception {
		PaymentVO paymentVO = paymentMapper.selectPayment(pm_num);
		return paymentVO;
	}
	
	// 결제 성공시 결제 테이블에 insert
	@Override
	public int insertPayment(PaymentVO paymentVO) throws Exception {
		return  paymentMapper.insertPayment(paymentVO);
		
	}
	
	// 결제 시 회원 테이블에 누적금액 상승 update
	@Override
	public int maxPrice(int user_maxPrice, String user_id) throws Exception {
		return paymentMapper.maxPrice(user_maxPrice, user_id);
	}
	
//	// 결제 시 누적구매횟수를 증가시키는 메서드 update
//	@Override
//	public int purchaseCnt(String b_name , int b_purchase) throws Exception {
//		return paymentMapper.purchaseCnt(b_name,b_purchase);
//	} 
	
	// 결제 성공시 도서 테이블에서 재고 차감, 누적 구매 횟수 증가 update
	@Override
	public int updateBook(int b_stock, String b_name) throws Exception {
		return paymentMapper.updateBook(b_stock, b_name);
	}
	
	// 결제 후 장바구니 삭제
	@Override
	public void deleteCart(String user_id) throws Exception {
		paymentMapper.deleteCart(user_id) ;
	}
	
	
	
}
