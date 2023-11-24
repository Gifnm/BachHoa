package com.spring.main.api;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.EmployeeService;
import com.spring.main.model.Authority;
import com.spring.main.model.Employee;
import com.spring.main.model.Role;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api/")
public class EmployeeAPI {
	@Autowired
	EmployeeService emService;

	@GetMapping("employees/getAll")
	public List<Employee> getAll() {
		return emService.findAll();

	}
	
	@GetMapping("authorities")
	public List<Authority> getAllRoles() {
		return emService.findAllRoles();
	}
	
	@DeleteMapping("authorities/deleteBy2ID/{roleID}/{employeeID}")
	public void deleteAuth(@PathVariable("roleID") String roleID,@PathVariable("employeeID") Integer employeeID ) {
		emService.deleteAuth(roleID, employeeID);
	}
	
	@PutMapping("employee/updateRoles/{roleID}/{employeeID}")
	public void updateRole(@PathVariable("roleID") String roleID,@PathVariable("employeeID") Integer employeeID)
	{
		emService.updateRoles(roleID, employeeID);
		// return emService.updateRoles(roleID, employeeID);
	}

	@GetMapping("authorities")
	public List<Authority> getAllRoles() {
		return emService.findAllRoles();
	}

	@DeleteMapping("authorities/deleteBy2ID/{roleID}/{employeeID}")
	public void deleteAuth(@PathVariable("roleID") String roleID, @PathVariable("employeeID") Integer employeeID) {
		emService.deleteAuth(roleID, employeeID);
	}

	@PutMapping("employee/updateRoles/{roleID}/{employeeID}")
	public void updateRole(@PathVariable("roleID") String roleID, @PathVariable("employeeID") Integer employeeID) {
		emService.updateRoles(roleID, employeeID);
		// return emService.updateRoles(roleID, employeeID);
	}

	@GetMapping("employee/findByEmail/{email}")
	public Employee findByEmail(@PathVariable("email") String email) {
		//System.out.println(email + " Đang được sử dụng");
		return emService.findByEmail(email);

	}

	@GetMapping("employee/findByID/{employeeID}")
	public Employee getByID(@PathVariable("employeeID") Integer employeeID) {
		//System.out.println(employeeID + " đã được tìm thấy");
		return emService.findByID(employeeID);

	}
	
	@PostMapping("employee/insert")
	public Employee insert(@RequestBody Employee employee) {
		System.out.println("Thêm nv");
		// StoreID, Role null at first
		return emService.insert(employee);
	}

	@PutMapping("employee/update")
	public Employee update(@RequestBody Employee employee) {
		return emService.update(employee);
	}

	@PostMapping("employee/insert/authorities")
	public Authority insertAuth(@RequestBody Authority authority) {
		// System.out.println("thêm auth");
		return emService.insertAuth(authority);
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
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
		}

	}

	@GetMapping("findById/{id}")
	private ResponseEntity<Employee> login(@PathVariable("id") int id) {

		Employee employee = emService.findByID(id);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {

			return ResponseEntity.ok(employee);
		}
	}

}
