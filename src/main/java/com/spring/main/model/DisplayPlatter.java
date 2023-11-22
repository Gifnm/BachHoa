package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "display_platters")
public class DisplayPlatter {
	//
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
