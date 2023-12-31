package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.EmployeeService;
import com.spring.main.model.Employee;
import com.spring.main.model.Role;
import com.spring.main.model.Store;

//import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class EmployeeAPI {
	@Autowired
	EmployeeService emService;

	@GetMapping("employees/getAll")
	public List<Employee> getAll() {
		return emService.findAll();

	}

	@GetMapping("employee/findByEmail/{email}")
	public Employee findByEmail(@PathVariable("email") String email) {
		return emService.findByEmail(email);

	}

	@GetMapping("employee/findByID/{employeeID}")
	public Employee getByID(@PathVariable("employeeID") Integer employeeID) {
		// System.out.println(employeeID + "hm");
		return emService.findByID(employeeID);

	}

	@PostMapping("insert")
	public Employee insert(@RequestBody Employee employee) {
		System.out.println("Insert");
		Store store = new Store();
		store.setStoreID(1);
		Role role = new Role();
		role.setRoleID("bhoa");
		// employee.setRole(role);
		employee.setStore(store);
		employee.setActive(true);
		emService.insert(employee);
		return employee;
	}

	@GetMapping("login/{passW}/{user}")
	private ResponseEntity<Employee> login(@PathVariable("passW") String pass, @PathVariable("user") int user) {
		System.out.println("Login: " + user + " - " + pass);
		Employee employee = emService.findByID(user);
		System.out.println("-------");

		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			System.out.println("Tim thay nhan vien");
			if (employee.getPassword().equals(pass)) {
				System.out.println("Password is correct");
				return ResponseEntity.ok(employee);
			} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);}
		}

	}
	@GetMapping("findById/{id}")
	private ResponseEntity<Employee> login( @PathVariable("id") int id) {

		Employee employee = emService.findByID(id);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
	
				return ResponseEntity.ok(employee);
		}}

}
