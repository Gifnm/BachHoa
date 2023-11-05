package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.ShipmentBatchDetail;

//import jakarta.transaction.Transactional;

public interface ShipmentBacthDetailJPA  extends JpaRepository<ShipmentBatchDetail, String>{
// Lay danh sach dot cham hang chi tiet theo ma cua hang
	
}
