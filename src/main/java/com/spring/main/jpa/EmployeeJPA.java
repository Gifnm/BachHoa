package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Employee;


public interface EmployeeJPA extends JpaRepository<Employee, Integer>{
  @Query(value = "Select * from employees where employeeID  = ?1 and storeID = ?2", nativeQuery = true)
    Employee getByID(String employeeID, int storeID);

    @Query(value = "Select * from employees where (employeeID = ?1 or employeeName like ?1 or roleID like ?1 or email like ?1) and storeID = ?2", nativeQuery = true)
    List<Employee> findByKeyword(String keyword, int storeId);

    @Query(value = "Select * from employees where storeID = ?1", nativeQuery = true)
    List<Employee> getByStoreId(int storeId);
}
