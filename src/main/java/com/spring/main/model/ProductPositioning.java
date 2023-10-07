package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "productPositioning")
public class ProductPositioning {
	@Id
	@Column(name = "proPosID")
	private Integer id;

	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name = "disPlaID")
	private DisplayPlatter displayPlatter;

	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name = "disSheID")
	private DisplayShelves displayShelves;

	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "displayQuantity")
	private int displayQuantity;
	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name = "storeID")
	private Store store;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DisplayPlatter getDisplayPlatter() {
		return displayPlatter;
	}

	public void setDisplayPlatter(DisplayPlatter displayPlatter) {
		this.displayPlatter = displayPlatter;
	}

	public DisplayShelves getDisplayShelves() {
		return displayShelves;
	}

	public void setDisplayShelves(DisplayShelves displayShelves) {
		this.displayShelves = displayShelves;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getDisplayQuantity() {
		return displayQuantity;
	}

	public void setDisplayQuantity(int displayQuantity) {
		this.displayQuantity = displayQuantity;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
