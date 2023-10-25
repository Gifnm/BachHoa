package com.spring.main.model;

import lombok.Data;

@Data
public class Invoice {
	private String productName;
	private int quantity;
	private String price;
	private String totalAmount;
}
