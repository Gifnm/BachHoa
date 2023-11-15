package com.spring.main.api;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(value = "employee/updatePhoto", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public void updatePhoto(@RequestPart("file") MultipartFile hinhAnh) throws IllegalStateException, IOException {
		emService.uploadImage(hinhAnh);
	}

	@PostMapping("employee/updateInformation")
	public Employee updateInformation(@RequestBody Employee e) {
		emService.updateInformation(e);
		return e;
	}

	@GetMapping("login/{passW}/{user}")
	public Employee login(@PathVariable("passW") String pass, @PathVariable("user") int user) {
		System.out.println("Login: " + user + " - " + pass);
		Employee employee = emService.findByID(user);
		if (employee == null) {

			return null;
		} else {
			System.out.println("j");
			if (employee.getPassword().equals(pass)) {
				return employee;
			}
			return null;

		}

	}

}
