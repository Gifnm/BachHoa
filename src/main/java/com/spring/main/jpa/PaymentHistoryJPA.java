package com.spring.main.jpa;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.PaymentHistory;

public interface PaymentHistoryJPA extends JpaRepository<PaymentHistory, Integer>{
	@Query("SELECT o FROM PaymentHistory o WHERE o.employee.employeeID = ?1 ORDER BY o.timeReceived DESC")
	Page<PaymentHistory> findByEmployee(Integer employeeID, Pageable page);
	
	@Query("SELECT o FROM PaymentHistory o WHERE o.timePay >= ?1 AND o.timePay <= ?2")
	Page<PaymentHistory> findByDate(Timestamp fromDate, Timestamp toDate, Pageable page);
}
