package com.spring.main.jpa;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.InventoryHistory;

public interface InventoryHistoryJPA extends JpaRepository<InventoryHistory, String>{
	@Query("select o from InventoryHistory o where o.countingTime >= ?1 and o.countingTime <= ?2 and o.store.storeID = ?3")
	Page<InventoryHistory> findByDate(Timestamp fromDate, Timestamp toDate, Integer storeID, Pageable page);
}
