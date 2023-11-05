package com.spring.main.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
