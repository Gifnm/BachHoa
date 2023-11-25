package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "storeID")
	private int storeID;
	
	@Column(name = "storeName")
	private String storeName;

	@Column(name = "address")
	private String address;

	@Column(name = "size")
	private String size;

	public Store(int storeID) {	
		this.storeID = storeID;
	}
}