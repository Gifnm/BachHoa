package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.Employee;

public interface EmployeeJPA extends JpaRepository<Employee, Integer>{

}
