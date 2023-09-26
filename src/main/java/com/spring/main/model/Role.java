package com.spring.main.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "roleID")
	private String roleID;

	@Column(name = "workRole")
	private String workRole;

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getWorkRole() {
		return workRole;
	}

	public void setWorkRole(String workRole) {
		this.workRole = workRole;
	}

//    @OneToMany(mappedBy = "role")
//    private List<Employee> employees;
//
//    @OneToMany(mappedBy = "role")
//    private List<RoleBasedSalary> roleBasedSalaries;

}
