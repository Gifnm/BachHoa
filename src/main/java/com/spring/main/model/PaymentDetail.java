package com.spring.main.model;

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
@Table(name = "payment_detail")
public class PaymentDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "paymentID")
	private PaymentHistory paymentHistory;

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
