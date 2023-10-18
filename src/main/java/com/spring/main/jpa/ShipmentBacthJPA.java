package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.ShipmentBatch;

public interface ShipmentBacthJPA  extends JpaRepository<ShipmentBatch, String>{
	@Query("Update ShipmentBatch  o set o.situation = true where o.shiBatID =:shiBatID")
	boolean setFinish(@Param("shiBatID") String shiBatID);
	
}
