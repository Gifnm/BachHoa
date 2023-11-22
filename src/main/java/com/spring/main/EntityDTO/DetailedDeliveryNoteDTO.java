package com.spring.main.EntityDTO;

public class DetailedDeliveryNoteDTO {
    private String productID;
    private int quantity;
    private int index;

    public String getProductID() {
        return this.productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
