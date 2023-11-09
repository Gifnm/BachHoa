package com.spring.main.model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_note")
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

	public DeliveryNote(String id, Store store, Employee employee, Timestamp timeCreate, Timestamp timeCompleted) {
		this.id = id;
		this.store = store;
		this.employee = employee;
		this.timeCreate = timeCreate;
		this.timeCompleted = timeCompleted;
	}

	public DeliveryNote() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Timestamp getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Timestamp timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

}
