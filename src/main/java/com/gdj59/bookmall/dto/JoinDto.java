package com.gdj59.bookmall.dto;

import lombok.Data;

@Data
public class JoinDto {
	private String user_id;
	private String user_pw;
	private String zipcode;
	private String roadAddress;
	private String jibunAddress;
	private String namujiAddress;
	private String hp2;
	private String hp3;
}
