package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product_is_out")
@IdClass(ProductIsOutID.class)
@Data
public class ProductIsOut {
	@Id
	private String productID;
	@Id
	private String storeID;
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;
	@ManyToOne
	@MapsId("storeID")
	@JoinColumn(name = "storeID")
	private Store store;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "imageDate")
	private String imageDate;
	@Column(name = "imageQuantity")
	private String imageQuantity;
	@ManyToOne()
	@JoinColumn(name = "employeeID")
	private Employee employee;
	@Column(name = "classifly")
	private String classifly;
	@Column(name = "note")
	private String note;
	@Column(name = "status")
	private Boolean status;
}
