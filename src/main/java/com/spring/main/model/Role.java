package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
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

//	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "employees")
//    private List<Employee> employees;
//
//    @OneToMany(mappedBy = "role")
//    private List<RoleBasedSalary> roleBasedSalaries;

}
