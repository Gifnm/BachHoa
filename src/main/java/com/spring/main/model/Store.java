package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "storeID")
	private int storeID;

	@Column(name = "address")
	private String address;

	@Column(name = "size")
	private String size;

	public Store(int storeID) {

		this.storeID = storeID;
	}

	public Store() {

	}

	public Store(int storeID, String address, String size) {

		this.storeID = storeID;
		this.address = address;
		this.size = size;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	// @OneToMany(mappedBy = "store")
	// private List<Warehouse> warehouses;
	//
	// @OneToMany(mappedBy = "store")
	// private List<Bill> bills;
	//
	// @OneToMany(mappedBy = "store")
	// private List<InventoryHistory> inventoryHistories;
	//
	// @OneToMany(mappedBy = "store")
	// private List<DisplayShelves> displayShelves;

}
