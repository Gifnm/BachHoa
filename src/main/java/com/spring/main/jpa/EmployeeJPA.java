package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Employee;

public interface EmployeeJPA extends JpaRepository<Employee, Integer>{
	@Query("SELECT o FROM Employee o WHERE o.email = ?1")
	Employee findbyEmail(String email);
}
