package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PurchaseHistoryID.class)
@EqualsAndHashCode
@Table(name = "purchase_history")
public class PurchaseHistory {
	@Id
	private String id;
	@Id
	private String productID;
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id")
	private DeliveryNote deliveryNote;
	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;
	@Column(name = "sysInventory")
	private int sysInventory;
	@Column(name = "quantityReceived")
	private int quantityReceived;
	@Column(name = "confirmedQuantity")
	private int confirmedQuantity;
	@Column(name = "totalAmount")
	private Float totalAmount;
}
