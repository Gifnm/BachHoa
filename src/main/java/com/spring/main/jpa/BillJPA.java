package com.spring.main.jpa;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Bill;

public interface BillJPA extends JpaRepository<Bill, String>{
	
	@Query("SELECT o FROM Bill o WHERE o.timeCreate between ?1 AND ?2")
	List<Bill> SearchBetween2Date(Date fromDate, Date toDate);
	
//	@Query("SELECT o FROM Bill o WHERE o.billID LIKE ?1")
//	List<Bill> findByBillID(String billID);
	
	@Query("SELECT o.billID FROM Bill o")
	List<String> getBillID();
}
