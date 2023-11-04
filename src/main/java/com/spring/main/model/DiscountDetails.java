package com.spring.main.model;

import java.sql.Date;

//import jakarta.persistence.Column;
//import jakarta.persistence.EmbeddedId;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@IdClass(DiscountDetailID.class)
@EqualsAndHashCode
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
	//
	// public String getDisID() {
	// return disID;
	// }
	//
	// public void setDisID(String disID) {
	// this.disID = disID;
	// }
	//
	// public Discount getDiscount() {
	// return discount;
	// }
	//
	// public void setDiscount(Discount discount) {
	// this.discount = discount;
	// }
	//
	// public Store getStore() {
	// return store;
	// }
	//
	// public void setStore(Store store) {
	// this.store = store;
	// }
	//
	// public Product getProduct() {
	// return product;
	// }
	//
	// public void setProduct(Product product) {
	// this.product = product;
	// }
	//
	// public boolean isActivity() {
	// return activity;
	// }
	//
	// public void setActivity(boolean activity) {
	// this.activity = activity;
	// }
	//
	// public Date getStartTime() {
	// return startTime;
	// }
	//
	// public void setStartTime(Date startTime) {
	// this.startTime = startTime;
	// }
	//
	// public Date getEndTime() {
	// return endTime;
	// }
	//
	// public void setEndTime(Date endTime) {
	// this.endTime = endTime;
	// }

}
