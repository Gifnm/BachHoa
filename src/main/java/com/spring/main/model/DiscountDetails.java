package com.spring.main.model;

import java.sql.Date;
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
@IdClass(DiscountDetailID.class)
@Table(name = "discount_details")
public class DiscountDetails {
	@Id
	private String disID;
	
	@Id
	private int storeID;
	
	@Id
	private String productID;

	@ManyToOne
	@MapsId("disID")
	@JoinColumn(name = "disID")
	private Discount discount;

	@ManyToOne
	@MapsId("storeID")
	@JoinColumn(name = "storeID")
	private Store store;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "activity")
	private boolean activity;

	@Column(name = "startTime")
	private Date startTime;

	@Column(name = "endTime")
	private Date endTime;

}
