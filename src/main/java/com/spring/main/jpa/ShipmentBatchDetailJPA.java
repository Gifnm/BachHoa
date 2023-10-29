package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

import jakarta.transaction.Transactional;

public interface ShipmentBatchDetailJPA extends JpaRepository<ShipmentBatchDetail, String> {
/*1. Lay danh sach ShipmentBatchDetail theo shiBatID
 * 
 * */
	  List<ShipmentBatchDetail> getByshiBatID(String shiBatID);
	  
	  ShipmentBatchDetail getByProductID(String productID);
}
