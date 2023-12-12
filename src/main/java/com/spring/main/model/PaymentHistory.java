package com.spring.main.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "payment_history")
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "adminID")
	private Employee admin;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@Column(name = "time_pay")
	private Timestamp timePay;

	@Column(name = "time_received")
	private Timestamp timeReceived;
	
	@Column(name = "totalAmount")
	private float totalAmount;

	@Column(name = "totalReceived")
	private float totalReceived;

	@Column(name = "paied")
	private int paied;

	@Column(name = "500k")
	private int vnd500;

	@Column(name = "200k")
	private int vnd200;

	@Column(name = "100k")
	private int vnd100;

	@Column(name = "50k")
	private int vnd50;

	@Column(name = "20k")
	private int vnd20;

	@Column(name = "10k")
	private int vnd10;
	
	@Column(name = "5k")
	private int vnd5;
	
	@Column(name = "2k")
	private int vnd2;
	
	@Column(name = "1k")
	private int vnd1;
}
