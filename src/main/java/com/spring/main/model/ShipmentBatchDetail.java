package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@IdClass(ShipmentBatchDetailID.class)
@EqualsAndHashCode
@Table(name = "shipment_batch_details")
public class ShipmentBatchDetail {
	@Id
	@Column(name = "shiBatID")

	private String shiBatID;
	@Id
	@Column(name = "productID")
	private String productID;
//
	@ManyToOne
	@MapsId
	@JoinColumn(name = "shiBatID")
	private ShipmentBatch shipmentBatch;
	@ManyToOne
	@MapsId
	@JoinColumn(name = "productID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	@Column(name = "situation")
	private boolean situation;

	@Column(name = "pictureURL")
	private String pictureURL;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "storeID")
	private int storeID;

}
