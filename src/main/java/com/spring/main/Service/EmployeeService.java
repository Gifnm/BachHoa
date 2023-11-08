package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeJPA employeeJPA;

	public List<Employee> findAll() {
		List<Employee> list = employeeJPA.findAll();
		return list;
	}

	public Employee create(Employee employee) {
		return employeeJPA.save(employee);
	}
	public void insert(Employee employee) {
		employeeJPA.save(employee);
	}

	public void detele(Integer id) {
		employeeJPA.deleteById(id);

	}

	public Employee findByID(Integer emloyeeid) {
		Employee employee = employeeJPA.findById(emloyeeid).get();
		return employee;
	}

	private void save(Employee employee){
		employeeJPA.save(employee);
	}

	public List <Employee> getAll(){
		return employeeJPA.findAll();
	} 

	public Employee update(Employee employee) {
		return employeeJPA.save(employee);
	}
}


