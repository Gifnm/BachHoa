package com.spring.main.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "bills")
public class Bill {
	@Id
	@Column(name = "billID")
	private String billID;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@Column(name = "totalAmount")
	private float totalAmount;

	@Column(name = "timeCreate")
	private Timestamp timeCreate;

	@Column(name = "cash")
	private float cash;
	
	@Column(name = "reduced")
	private float reduced;
//
//	public String getBillID() {
//		return billID;
//	}
//
//	public void setBillID(String billID) {
//		this.billID = billID;
//	}
//
//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	public float getTotalMoney() {
//		return totalMoney;
//	}
//
//	public void setTotalMoney(float totalAmount) {
//		this.totalMoney = totalAmount;
//	}
//
//	public Timestamp getTimeCreate() {
//		return timeCreate;
//	}
//
//	public void setTimeCreate(Timestamp timeCreate) {
//		this.timeCreate = timeCreate;
//	}
//
//	public float getCash() {
//		return cash;
//	}
//
//	public void setCash(float cash) {
//		this.cash = cash;
//	}
//
//	public float getReduced() {
//		return reduced;
//	}
//
//	public void setReduced(float reduced) {
//		this.reduced = reduced;
//	}

}
