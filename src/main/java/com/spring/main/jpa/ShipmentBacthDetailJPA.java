package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.ShipmentBatchDetail;

public interface ShipmentBacthDetailJPA extends JpaRepository<ShipmentBatchDetail, String> {
    // Lay danh sach dot cham hang chi tiet theo ma cua hang
    List<ShipmentBatchDetail> getByshiBatID(String shiBatID);

}
