package com.spring.main.jpa;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Bill;

public interface BillJPA extends JpaRepository<Bill, String> {

	@Query("SELECT o FROM Bill o WHERE o.store.storeID = ?3 AND o.timeCreate >= ?1 AND o.timeCreate <= ?2 ORDER BY timeCreate DESC")
	List<Bill> findAllByTimeCreateBetween(Timestamp fromDate, Timestamp toDate, int storeId);

	@Query("SELECT o.billID FROM Bill o WHERE o.store.storeID = ?1")
	List<String> getBillID(Integer storeID);

	@Query("SELECT o FROM Bill o WHERE o.store.storeID = ?1")
	List<Bill> findAllByStoreID(Integer storeID);

	@Query("SELECT o FROM Bill o WHERE o.timeCreate >= ?1 AND o.timeCreate <= ?2 AND o.store.storeID = ?3 ORDER BY timeCreate DESC")
	Page<Bill> SearchBetween2Date(Timestamp fromDate, Timestamp toDate, Integer storeID, Pageable page);

	@Query("SELECT o FROM Bill o WHERE o.employee.employeeID = ?1 AND (o.timeCreate >= ?2 AND o.timeCreate <= ?3)")
	List<Bill> findByEmployeeAndDate(Integer employeeID, Timestamp fromDate, Timestamp toDate);

}
