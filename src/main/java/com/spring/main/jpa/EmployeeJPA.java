package com.spring.main.jpa;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.main.model.Employee;

public interface EmployeeJPA extends JpaRepository<Employee, Integer>{
	@Query("SELECT o FROM Employee o WHERE o.email = ?1")
	Employee findbyEmail(String email);
	
//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE employees SET email = ?1, age = ?2, address = ?3 WHERE employeeID = ?4", nativeQuery = true)
//	void updateInfomation(String email, Date age, String address, Integer employeeID);
}
