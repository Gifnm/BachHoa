package com.spring.main.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.EmployeeService;
import com.spring.main.model.Employee;
import com.spring.main.model.Role;
import com.spring.main.model.Store;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
public class EmployeeController {
@Autowired
EmployeeService emService;
	@GetMapping("/bachhoa/api/employees")
	public List<Employee> getAll(){
		return emService.findAll();
		
	}
	@GetMapping("/bachhoa/api/employee/{id}")
	public Employee getByID(@PathVariable Integer id) {
		return emService.findByID(id);
		
	}
	@PostMapping("/bachhoa/api/insert")
	public Employee insert(@RequestBody Employee employee) {
		System.out.println("Insert");
		Store store = new Store();
		store.setStoreID(1);
		Role role = new Role();
		role.setRoleID("bhoa");
		employee.setRole(role);
		employee.setStore(store);
		employee.setActivity(true);
		emService.insert(employee);
		return employee;
	}
	
}
