package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "display_shelves")
public class DisplayShelves {
	@Id
	@Column(name = "disSheID")
	private int disSheID;

	@Column(name = "shelfName")
	private String shelfName;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	public int getDisSheID() {
		return disSheID;
	}

	public void setDisSheID(int disSheID) {
		this.disSheID = disSheID;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
