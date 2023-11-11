package com.spring.main.jpa;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Bill;
import com.spring.main.model.Employee;

public interface BillJPA extends JpaRepository<Bill, String> {

	@Query("SELECT o FROM Bill o WHERE o.timeCreate >= ?1 AND o.timeCreate <= ?2 ORDER BY timeCreate DESC")
	List<Bill> findAllByTimeCreateBetween(Timestamp fromDate, Timestamp toDate);

	@Query("SELECT o.billID FROM Bill o")
	List<String> getBillID();

	@Query("SELECT o FROM Bill o WHERE o.timeCreate >= ?1 AND o.timeCreate <= ?2")
	Page<Bill> SearchBetween2Date(Timestamp fromDate, Timestamp toDate, Pageable page);
	
	@Query("SELECT o FROM Bill o WHERE o.employee.employeeID = ?1 AND (o.timeCreate >= ?2 AND o.timeCreate <= ?3)")
	List<Bill> findByEmployeeAndDate(Integer employeeID, Timestamp fromDate, Timestamp toDate);
}
