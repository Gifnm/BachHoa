package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "displayPlatter")
public class DisplayPlatter {
	@Id
	@Column(name = "disPlaID")
	private int disPlaID;

	@Column(name = "rowName")
	private String rowName;
 @OneToOne
 @JoinColumn(name = "StoreID")
 private Store store;
	public int getDisPlaID() {
		return disPlaID;
	}

	public DisplayPlatter() {
	
	}

	public DisplayPlatter(int disPlaID) {
		this.disPlaID = disPlaID;
	}

	public DisplayPlatter(int disPlaID, String rowName, Store store) {
	
		this.disPlaID = disPlaID;
		this.rowName = rowName;
		this.store = store;
	}

	public void setDisPlaID(int disPlaID) {
		this.disPlaID = disPlaID;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
