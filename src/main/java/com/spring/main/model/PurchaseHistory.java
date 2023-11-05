package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PurchaseHistory.class)
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
}
