package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "storeID")
	private int storeID;
	
	@Column(name = "storeName")
	private String storeName;

	@Column(name = "address")
	private String address;

	@Column(name = "size")
	private String size;

	public Store(int storeID) {	
		this.storeID = storeID;
	}
//
//	public Store() {
//	
//	}
//
//	public Store(int storeID, String address, String size) {
//		
//		this.storeID = storeID;
//		this.address = address;
//		this.size = size;
//	}
//
//	public int getStoreID() {
//		return storeID;
//	}
//
//	public void setStoreID(int storeID) {
//		this.storeID = storeID;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getSize() {
//		return size;
//	}
//
//	public void setSize(String size) {
//		this.size = size;
//	}

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
