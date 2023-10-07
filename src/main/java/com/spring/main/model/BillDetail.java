package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "billDetail")
public class BillDetail {
	@Id
	@Column(name = "billID")
	private String billID;

	@ManyToOne
	@MapsId("billID")
	@JoinColumn(name = "billID")
	private Bill bill;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
