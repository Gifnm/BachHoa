package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	EmployeeService emService;

	@GetMapping("/bachhoa/api/employees")
	public List<Employee> getAll() {
		return emService.getAll();

	}

	@GetMapping("findById/{id}")
	public Employee getByID(@PathVariable Integer id) {
		System.out.println(id + "hm");
		return emService.findByID(id);

	}

	@PostMapping("insert")
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

	@GetMapping("login/{passW}/{user}")
	public Employee login(@PathVariable("passW") String pass, @PathVariable("user") int user) {
		System.out.println("Login: " + user + " - " + pass);
		Employee employee = emService.findByID(12);
		if (employee == null) {

			return null;
		} else {
			System.out.println("j");
			if (employee.getPasswork().equals(pass)) {
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

  @PostMapping("/bachhoa/api/employee")
    public Employee create(@RequestBody Employee employee) {
        return emService.create(employee);
    }

	@PostMapping("/bachhoa/api/employee")
    public Employee update(@PathVariable("id") String id,@RequestBody Employee employee) {
        return emService.update(employee);
    }
	@DeleteMapping("/bachhoa/api/employee/{id}")
    public void delete(@PathVariable ("id") Integer id ) {
        emService.detele(id);
    }
}
