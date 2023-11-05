package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

	/**
	 * Luu moi & cap nhat thong tin 1 nhan vien
	 * 
	 * @param employee Object nhan vien
	 */
	public void insert(Employee employee) {
		employeeJPA.save(employee);
	}

	/**
	 * Xoa mot nhan vien
	 * 
	 * @param id Ma so nhan vien
	 */
	public void detele(Integer id) {
		employeeJPA.deleteById(id);

	}

	/**
	 * Lay 1 nhan vien
	 * 
	 * @param id Ma so nhan vien
	 */
	public Employee findByID(Integer id) {
		Employee employee = employeeJPA.findById(id).get();
		return employee;
	}
}
