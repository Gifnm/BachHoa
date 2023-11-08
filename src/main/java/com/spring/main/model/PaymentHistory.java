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

	@Column(name = "time_pay")
	private Timestamp timePay;

	@Column(name = "time_received")
	private Timestamp timeReceived;
	
	@Column(name = "totalAmount")
	private float totalAmount;


	@Column(name = "paied")
	private boolean paied;
}
