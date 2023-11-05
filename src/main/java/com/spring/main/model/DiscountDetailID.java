package com.spring.main.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DiscountDetailID implements Serializable {
	private String disID;
	private int storeID;
	private String productID;

}
