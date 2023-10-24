package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@IdClass(BillDetailID.class)
@EqualsAndHashCode
@Table(name = "bill_details")
public class BillDetail {
	@Id
	private String billID;
	
	@Id
	private String productID;
	
	@ManyToOne
	@MapsId("billID")
	@JoinColumn(name = "billID")
	private Bill bill;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "totalAmount")
	private float totalAmount;
}
