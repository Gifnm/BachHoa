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

@Data
@Entity
@IdClass(BillDetailID.class)
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
