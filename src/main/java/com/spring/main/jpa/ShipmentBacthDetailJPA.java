package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

//import jakarta.transaction.Transactional;

public interface ShipmentBacthDetailJPA  extends JpaRepository<ShipmentBatchDetail, String>{
// Lay danh sach dot cham hang chi tiet theo ma cua hang
	
}
