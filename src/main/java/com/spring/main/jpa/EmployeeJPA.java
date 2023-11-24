package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.main.model.Employee;

public interface EmployeeJPA extends JpaRepository<Employee, Integer> {
  @Query(value = "Select * from employees where employeeID  = ?1 and storeID = ?2", nativeQuery = true)
  Employee getByID(String employeeID, int storeID);

  @Query(value = "Select * from employees where (employeeID = ?1 or employeeName like ?1 or roleID like ?1 or email like ?1) and storeID = ?2", nativeQuery = true)
  List<Employee> findByKeyword(String keyword, int storeId);

  @Query(value = "Select * from employees where storeID = ?1 and active = true", nativeQuery = true)
  List<Employee> getByStoreId(int storeId);

  @Query("SELECT o FROM Employee o WHERE o.email = ?1")
  Employee findbyEmail(String email);
  
  @Transactional
	@Modifying
	@Query(value = "UPDATE employees SET roleID = null, storeID = null, active = 0 WHERE employeeID = ?1", nativeQuery = true)
	void DeleteWait(Integer employeeID);
  
  @Query("SELECT o FROM Employee o WHERE o.store.storeID = ?1 AND o.active = false")
  List<Employee> getRequest(Integer storeID);

  @Transactional
	@Modifying
	@Query(value = "UPDATE employees SET active = 1 where employeeID = ?1", nativeQuery = true)
	void accept(Integer employeeID);

}
