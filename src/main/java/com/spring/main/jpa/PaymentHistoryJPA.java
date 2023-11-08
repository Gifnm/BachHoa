package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.PaymentHistory;

public interface PaymentHistoryJPA extends JpaRepository<PaymentHistory, Integer>{
	@Query("SELECT o FROM PaymentHistory o WHERE o.employee.employeeID = ?1")
	List<PaymentHistory> findByEmployee(Integer employeeID);
}
