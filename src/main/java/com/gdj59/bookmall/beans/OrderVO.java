package com.gdj59.bookmall.beans;

import lombok.Data;

@Data
public class OrderVO {

	private int o_num;
	private String o_date; // java util Date 사용여부 확인
	private String o_id;
	private int o_b_price;
	private int o_b_num;


}
