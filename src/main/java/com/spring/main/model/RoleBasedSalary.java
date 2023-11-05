package com.spring.main.model;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roleBasedSalary")
public class RoleBasedSalary {
	@Id
	@Column(name = "rbsID")
	private String rbsID;

	@ManyToOne
	@JoinColumn(name = "roleID")
	private Role role;

	@Column(name = "unit")
	private String unit;

	@Column(name = "vung")
	private int vung;

	public String getRbsID() {
		return rbsID;
	}

	public void setRbsID(String rbsID) {
		this.rbsID = rbsID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getVung() {
		return vung;
	}

	public void setVung(int vung) {
		this.vung = vung;
	}

}
