package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipment_batch_details")
@IdClass(ShipmentBatchDetailID.class)
public class ShipmentBatchDetail {
	@Id
	@Column(name = "shiBatID")

	private String shiBatID;
	@Id
	@Column(name = "productID")
	private String productID;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@Column(name = "situation")
	private boolean situation;

	@Column(name = "pictureURL")
	private String pictureURL;
	@Column(name = "quantity")
	private int quantity;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isSituation() {
		return situation;
	}

	public void setSituation(boolean situation) {
		this.situation = situation;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShiBatID() {
		return shiBatID;
	}

	public void setShiBatID(String shiBatID) {
		this.shiBatID = shiBatID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

}
