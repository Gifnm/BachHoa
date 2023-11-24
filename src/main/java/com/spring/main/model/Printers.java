package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "printers")
public class Printers {
	@Id
	@Column(name = "ipAddress")
	private String ipAddress;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	@Column(name = "nameOfPrinter")
	private String nameOfPrinter;
	@Column(name = "pageSize")
	private int pageSize;

	public Printers() {
	}

	public Printers(String ipAddress, Store store, String nameOfPrinter, int pageSize) {
		this.ipAddress = ipAddress;
		this.store = store;
		this.nameOfPrinter = nameOfPrinter;
		this.pageSize = pageSize;
	}

}
