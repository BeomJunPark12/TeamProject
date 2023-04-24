package com.gdj59.bookmall.beans;

import java.util.Date;

import com.gdj59.bookmall.service.UserService;

import lombok.Data;


@Data
public class PaymentVO {
	
	private UserService userService;
	
	
	private int pm_num;	// 결제번호
	private Date pm_date;	// 결제 날짜
	private String pm_id;	// 결제 아이디
	private int pm_b_price;	// 결제 금액
	private String pm_b_name;	//결제상품이름
	
	private String imp_uid; // 아임포트 거래 번호
    private String merchant_uid; // 가맹점 거래 ID
    private int paid_amount; // 결제 금액
    private String buyer_name; // 구매자 이름
    private String buyer_tel; // 구매자 전화번호
    private String buyer_addr; // 구매자 주소
	
	public PaymentVO() {};
	
	public PaymentVO(Date pm_date, String pm_id, int pm_b_price, String pm_b_name) {
		super();
		this.pm_date = pm_date;
		this.pm_id = pm_id;
		this.pm_b_price = pm_b_price;
		this.pm_b_name = pm_b_name;
	}
	
	
}
