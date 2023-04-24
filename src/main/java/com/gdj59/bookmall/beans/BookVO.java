package com.gdj59.bookmall.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO {
	private int b_num;
	private String b_name;
	private int b_price;
	private int b_stock;
	private String b_category;
	private int b_purchase;
}
