package com.spring.main.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.spring.main.model.ShipmentBatch;

public interface ShipmentBacthJPA extends JpaRepository<ShipmentBatch, String> {
	/* 1. 
	 * Chuyen trang thai dot cham hang = hoan tat (situation = true)
	 * 
	 */
	@Query("Update ShipmentBatch  o set o.situation = true where o.shiBatID =:shiBatID")
	boolean setFinish(@Param("shiBatID") String shiBatID);
 /* 2. Lay danh sach ban giao ca theo cua hang
  *  - Tham so: Store
  *  - Dung khi: 
  * */
	@Query("SELECT o FROM ShipmentBatch o WHERE o.store.storeID = :storeID AND o.situation = false" )
	List<ShipmentBatch> findByStore(@Param("storeID") int storeID);
}
