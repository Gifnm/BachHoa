package com.spring.main.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DetailedDeliveryNoteID implements Serializable{
	private String id;
	private String productID;
}
