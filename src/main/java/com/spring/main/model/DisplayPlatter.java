package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "displayPlatter")
public class DisplayPlatter {
	@Id
	@Column(name = "disPlaID")
	private int disPlaID;

	@Column(name = "rowName")
	private String rowName;

	public int getDisPlaID() {
		return disPlaID;
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

}
