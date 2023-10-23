package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.BillDetail;


public interface BillDetailJPA extends JpaRepository<BillDetail, String>{

	@Query("SELECT o FROM BillDetail o WHERE o.billID LIKE ?1")
	List<BillDetail> findByBillID(String billID);
	
}
