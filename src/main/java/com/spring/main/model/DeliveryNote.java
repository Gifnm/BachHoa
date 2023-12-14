package com.spring.main.model;

import javax.persistence.*;

import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "delivery_note")
public class DeliveryNote {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "timeCreate")
	private Timestamp timeCreate;

	@Column(name = "timeCompleted")
	private Timestamp timeCompleted;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	// Constructors, getters, and setters
}
