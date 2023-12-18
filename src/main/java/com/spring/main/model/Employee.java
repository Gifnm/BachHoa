package com.spring.main.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeeID")
	private int employeeID;

	@Column(name = "employeeName")
	private String employeeName;

	@Column(name = "age")
	private Date age;

	@Column(name = "address")
	private String address;

	@Column(name = "pictureURL")
	private String pictureURL;

	@Column(name = "firstWork")
	private Date firstWork;

	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id")
	private List<Authority> authorities;

	@Column(name = "active")
	private boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "employeeID"), inverseJoinColumns = @JoinColumn(name = "roleID"))

	private Set<Role> roles = new HashSet<>();

	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = this.roles.iterator();
		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getRoleID().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public Employee(int employeeID, String employeeName, Date age, String address, String pictureURL, Date firstWork,
			Store store, List<Authority> authorities, boolean active, String email, String password,
			Set<Role> roles) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.age = age;
		this.address = address;
		this.pictureURL = pictureURL;
		this.firstWork = firstWork;
		this.store = store;
		this.authorities = authorities;
		this.active = active;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Employee() {
	}

	public Employee(int employeeID) {

		this.employeeID = employeeID;
	}

}
