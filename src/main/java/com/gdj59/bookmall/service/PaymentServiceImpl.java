package com.gdj59.bookmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdj59.bookmall.beans.PaymentVO;
import com.gdj59.bookmall.dao.PaymentDao;

@Service
public class PaymentServiceImpl implements paymentService {
	@Autowired
	PaymentDao paymentDao;

	@Override
	public PaymentVO selectPayment(Integer pm_num) throws Exception {
		PaymentVO paymentVO = paymentDao.selectPayment(pm_num);
		return paymentVO;
	}

	// 결제 성공시 결제 테이블에 insert
	@Override
	public int savePayment(PaymentVO paymentVO) throws Exception {
		return paymentDao.insertPayment(paymentVO);
	}

	// 결제 시 회원 테이블에 누적금액 상승 update
	@Override
	public int saveMaxPrice(int user_maxPrice, String user_id) throws Exception {
		return paymentDao.maxPrice(user_maxPrice, user_id);
	}

	// 결제 시 누적구매횟수를 증가시키는 메서드 update
//	@Override
//	public int savePurchaseCnt(String b_name, int b_purchase) throws Exception {
//		return paymentDao.purchaseCnt(b_name, b_purchase);
//	}

	// 결제 성공시 도서 테이블에서 재고 차감 update
	@Override
	public int UpdateBook(int b_stock, String b_name) throws Exception {
		return paymentDao.updateBook(b_stock, b_name);
	}
	
	// 결제 후 장바구니 삭제
	@Override
	public void removeCart(String user_id) throws Exception {
		paymentDao.deleteCart(user_id);
	}

}
