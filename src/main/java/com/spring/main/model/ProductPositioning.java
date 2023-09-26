package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@ManyToOne
	@MapsId("disPlaID")
	@JoinColumn(name = "disPlaID")
	private DisplayPlatter displayPlatter;

	@ManyToOne
	@MapsId("disSheID")
	@JoinColumn(name = "disSheID")
	private DisplayShelves displayShelves;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "displayQuantity")
	private int displayQuantity;

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

}
