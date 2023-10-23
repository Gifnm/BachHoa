package com.spring.main.model;

import java.io.Serializable;


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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@IdClass(BillDetailID.class)
@EqualsAndHashCode
@Table(name = "bill_details")
public class BillDetail{
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
//
//	public Bill getBill() {
//		return bill;
//	}
//
//	public void setBill(Bill bill) {
//		this.bill = bill;
//	}
//
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public float getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(float totalAmount) {
//		this.totalAmount = totalAmount;
//	}
//	
//	public BillDetailID getBillDetailID() {
//		return billDetailID;
//	}
//
//	public void setBillDetailID(BillDetailID billDetailID) {
//		this.billDetailID = billDetailID;
//	}
//	
//
//	public BillDetail(BillDetailID billDetailID, Bill bill, Product product, int quantity, float totalAmount) {
//		super();
//		this.billDetailID = billDetailID;
//		this.bill = bill;
//		this.product = product;
//		this.quantity = quantity;
//		this.totalAmount = totalAmount;
//	}
//
//
//	public BillDetail() {
//		super();
//	}


//	@Embeddable
//	public class BillDetailID implements Serializable{
//		@Column
//		private String billID;
//		@Column
//		private String productID;
//		
//		public String getBillID() {
//			return billID;
//		}
//		public void setBillID(String billID) {
//			this.billID = billID;
//		}
//		public String getProductID() {
//			return productID;
//		}
//		public void setProductID(String productID) {
//			this.productID = productID;
//		}
//		public BillDetailID(String billID, String productID) {
//			super();
//			this.billID = billID;
//			this.productID = productID;
//		}
//		public BillDetailID() {
//			super();
//		}
//		
//		
//		
//	}
}
