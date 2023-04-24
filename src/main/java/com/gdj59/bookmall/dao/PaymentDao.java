package com.gdj59.bookmall.dao;

import org.apache.ibatis.annotations.Param;

import com.gdj59.bookmall.beans.PaymentVO;

public interface PaymentDao {
	// 결제 테이블 조회 
	PaymentVO selectPayment(Integer pm_num) throws Exception;
	
	// 결제 성공시 결제 테이블에 insert 
	int insertPayment(PaymentVO paymentVO) throws Exception;
	
	// 결제 시 회원 테이블에 누적금액 상승 update
	int maxPrice(@Param("user_maxPrice") int user_maxPrice, @Param("user_id") String user_id) throws Exception;
	
//	// 결제 시 누적구매횟수를 증가시키는 메서드 update
//	int purchaseCnt(@Param("b_name")String b_name ,@Param("b_purchase")int b_purchase) throws Exception;
	
	// 결제 성공시 도서 테이블에서 재고 차감 update
	int updateBook(@Param("b_stock") int b_stock, @Param("b_name") String b_name) throws Exception;
	
	// 결제 후 장바구니 삭제
	void deleteCart(String user_id) throws Exception;
}
