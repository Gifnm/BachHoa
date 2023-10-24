package com.spring.main.model;

public class BillDetailId {
	private String billID;
	private String productID;

	public BillDetailId() {

	}

	public BillDetailId(String billID, String productID) {
		this.billID = billID;
		this.productID = productID;
	}

	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

}