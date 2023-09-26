package com.spring.main.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeeID")
	private int employeeID;

	@Column(name = "employeeName")
	private String employeeName;

	@Column(name = "age")
	private Date age;

	@Column(name = "address")
	private String address;

	@Column(name = "pictureURL")
	private String pictureURL;

	@Column(name = "firstWork")
	private Date firstWork;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "roleID")
	private Role role;

	@Column(name = "activity")
	private boolean activity;
	@Column(name = "email")
	private String email;
	@Column(name = "passwork")
	private String passwork;

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Date getFirstWork() {
		return firstWork;
	}

	public void setFirstWork(Date firstWork) {
		this.firstWork = firstWork;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActivity() {
		return activity;
	}

	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

//    @OneToMany(mappedBy = "employee")
//    private List<Bill> bills;
//
//    @OneToMany(mappedBy = "employee")
//    private List<DetailWorkSchedule> detailWorkSchedules;
//
//    @OneToMany(mappedBy = "employee")
//    private List<InventoryHistory> inventoryHistories;
//
//    @OneToMany(mappedBy = "employee")
//    private List<ShipmentBatch> shipmentBatches;
//
//    @OneToMany(mappedBy = "employee")
//    private List<ShipmentBatchDetail> shipmentBatchDetails;

}
