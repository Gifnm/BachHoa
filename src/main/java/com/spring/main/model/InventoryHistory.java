package com.spring.main.model;

import java.sql.Date;
import java.sql.Timestamp;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_histories")
public class InventoryHistory {
	@Id
	@Column(name = "inHisID")
	private String inHisID;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "sysInventory")
	private int sysInventory;

	@Column(name = "inventoryCount")
	private int inventoryCount;

	@Column(name = "countingTime")
	private Timestamp countingTime;

	public String getInHisID() {
		return inHisID;
	}

	public void setInHisID(String inHisID) {
		this.inHisID = inHisID;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getSysInventory() {
		return sysInventory;
	}

	public void setSysInventory(int sysInventory) {
		this.sysInventory = sysInventory;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public Timestamp getCountingTime() {
		return countingTime;
	}

	public void setCountingTime(Timestamp countingTime) {
		this.countingTime = countingTime;
	}

}
