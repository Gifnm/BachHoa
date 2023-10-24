package com.spring.main.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class BillDetailID implements Serializable {
	private String billID;
	private String productID;
	
}