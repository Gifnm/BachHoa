package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
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
	@Column(name = "storeName")
	private String storeName;

	public Store(int storeID) {
	
		this.storeID = storeID;
	}

	public Store() {
	
	}

	public Store(int storeID, String address, String size, String storeName) {
		this.storeID = storeID;
		this.address = address;
		this.size = size;
		this.storeName = storeName;
	}




//    @OneToMany(mappedBy = "store")
//    private List<Warehouse> warehouses;
//
//    @OneToMany(mappedBy = "store")
//    private List<Bill> bills;
//
//    @OneToMany(mappedBy = "store")
//    private List<InventoryHistory> inventoryHistories;
//
//    @OneToMany(mappedBy = "store")
//    private List<DisplayShelves> displayShelves;

}
