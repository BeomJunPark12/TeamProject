package com.gdj59.bookmall.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

	private int user_num;
	private String user_id;
	private String user_pw;
	private String user_address;
	private String user_phone;
	private String user_maxPrice;
	private String user_grade;  // master admin vip basic 네개로 구분

	public UserVO(int user_num, String user_id, String user_pw, String user_address, String user_phone) {
		this.user_num = user_num;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_address = user_address;
		this.user_phone = user_phone;
	}

	public UserVO(String user_pw, String user_address, String user_phone) {
		super();
		this.user_pw = user_pw;
		this.user_address = user_address;
		this.user_phone = user_phone;
	}
//======================================================
	public UserVO(int user_num, String user_id, String user_pw, String user_grade) {
		this.user_num = user_num;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_grade = user_grade;
	}
//======================================================

	
	

}
