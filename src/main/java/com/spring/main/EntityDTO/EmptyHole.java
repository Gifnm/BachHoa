package com.spring.main.EntityDTO;

public class EmptyHole {
	private String productID;
	private int quantity;

	public EmptyHole() {

	}

	public EmptyHole(String productID, int quantity) {
		this.productID = productID;
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
