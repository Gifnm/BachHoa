package com.spring.main.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Employee;

public interface EmployeeJPA extends JpaRepository<Employee, Integer>{
	@Query("SELECT o FROM Employee o WHERE o.email = ?1")
	Employee findbyEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE employees SET roleID = ?1 WHERE employeeID = ?2", nativeQuery = true)
	void updateRole(String roleID, Integer employeeID);
}
