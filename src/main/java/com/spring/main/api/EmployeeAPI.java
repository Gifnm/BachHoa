package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.EmployeeService;
import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.model.Employee;
import com.spring.main.model.Role;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class EmployeeAPI {
	@Autowired
	EmployeeJPA employeeJPA;
	@Autowired
	EmployeeService emService;

	@GetMapping("employee")
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

	@GetMapping("/bachhoa/api/employees/search")
	public List<Employee> getByKeyword(String keyword, int storeId) {
		System.out.println("[EmplooyeeService:getByKeyWord():59]\n> calling repo with keyword '" + keyword + "'...");
		try {
			Integer.parseInt(keyword);
			System.out.println("[EmployeeService:getByKeyWord():62]\n> keyword after parse int: " + keyword);
			return employeeJPA.findByKeyword(keyword, storeId);
		} catch (Exception e) {
			keyword = "%" + keyword + "%";
			System.out.println("[EmployeeService:getByKeyWord():66]\n> keyword after makeup: " + keyword);
			return employeeJPA.findByKeyword(keyword, storeId);
		}
	}

	@GetMapping("/bachhoa/api/employee/{store-id}")
	public List<Employee> getAllByStoreId(int storeId) {
		return employeeJPA.getByStoreId(storeId);
	}

@GetMapping("employee/Request/{storeID}")
   public List<Employee> getRequest(@PathVariable("storeID") Integer id) {
	return emService.getRequest(id);
  }	

	@DeleteMapping("/bachhoa/api/employee/{id}")
	public void delete(@PathVariable("id") Integer id) {
		emService.detele(id);
	}
	
	@PutMapping("employeeDel/{id}")
	public void DeleteWait(@PathVariable("id") Integer id) {
		emService.DeleteWait(id);
	}
}
