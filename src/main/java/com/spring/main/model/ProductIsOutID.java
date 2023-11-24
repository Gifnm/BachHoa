package com.spring.main.model;

import java.io.Serializable;

import lombok.Data;
@Data
@SuppressWarnings("serial")
public class ProductIsOutID implements Serializable{
private String productID;
private int storeID;
}
