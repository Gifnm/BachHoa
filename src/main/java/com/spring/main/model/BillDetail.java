package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_details")
@IdClass(BillDetailId.class)
public class BillDetail {
	@Id
	@Column(name = "billID")
	private String billID;

	@Id
	@Column(name = "productID")
	private String productID;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "totalAmount")
	private float totalAmount;

	public String getId() {
		return billID;
	}

	public void setId(String id) {
		this.billID = id;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
}
