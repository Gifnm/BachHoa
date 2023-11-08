package com.spring.main.model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_note")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryNote {
	@Id
	@Column(name = "id")
	private String id;
	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;
	@Column(name = "timeCreate")
	private Timestamp timeCreate;
	@Column(name = "timeCompleted")
	private Timestamp timeCompleted;

}
