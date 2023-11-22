package com.spring.main.model;

import java.sql.Timestamp;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "shipment_batch_details")
public class ShipmentBatchDetail {
	@Id
	@Column(name = "shiBatID")
	private String shiBatID;

	@ManyToOne
	@MapsId("shiBatID")
	@JoinColumn(name = "shiBatID")
	private ShipmentBatch shipmentBatch;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@Column(name = "situation")
	private boolean situation;

	@Column(name = "pictureURL")
	private String pictureURL;
	@Column(name = "quantity")
	private int quantity;

	public String getShiBatID() {
		return shiBatID;
	}

	public void setShiBatID(String shiBatID) {
		this.shiBatID = shiBatID;
	}

	public ShipmentBatch getShipmentBatch() {
		return shipmentBatch;
	}

	public void setShipmentBatch(ShipmentBatch shipmentBatch) {
		this.shipmentBatch = shipmentBatch;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

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
}
