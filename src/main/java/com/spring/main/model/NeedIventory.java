package com.spring.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "needInventory")
public class NeedIventory {
	@Id
	@OneToMany()
	@JoinColumn(name = "productID")
	private Product product;
	@OneToMany()
	@JoinColumn(name = "storeID")
	private Store store;

	public NeedIventory(Product product, Store store) {
		this.product = product;
		this.store = store;
	}

	public NeedIventory() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
