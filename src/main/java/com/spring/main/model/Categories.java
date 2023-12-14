package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoriesID")
	private int categoriesID;

	@Column(name = "categoriesName", nullable = false)
	private String categoriesName;

	@Column(name = "storeID", nullable = false)
	private String storeID;

	public int getCategoriesID() {
		return categoriesID;
	}

	public void setCategoriesID(int categoriesID) {
		this.categoriesID = categoriesID;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

}
