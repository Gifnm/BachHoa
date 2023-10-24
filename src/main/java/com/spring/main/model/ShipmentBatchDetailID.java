package com.spring.main.model;

public class ShipmentBatchDetailID {
private String shiBatID;
private String productID;


public ShipmentBatchDetailID(String shiBatID, String productID) {
	this.shiBatID = shiBatID;
	this.productID = productID;
}


public ShipmentBatchDetailID() {
}


public String getShiBatID() {
	return shiBatID;
}
public void setShiBatID(String shiBatID) {
	this.shiBatID = shiBatID;
}
public String getProductID() {
	return productID;
}
public void setProductID(String productID) {
	this.productID = productID;
}


}
