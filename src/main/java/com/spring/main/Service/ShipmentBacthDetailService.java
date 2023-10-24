package com.spring.main.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthDetailJPA;
import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthDetailService {
	@Autowired
	ShipmentBacthDetailJPA shipmentBacthDetailJPA;

	/*
	 * Cac phuong thuc 1. Lay danh sach cham hang chi tiet theo cua hang 2. Cap nhat
	 * trang thai sau khi cham hang 3. Xoa san pham sau khi hoan tat ke
	 * 
	 */
	/*
	 * Lay danh sach cham hang chi tiet theo ma cua hang - Tham so: ma cua hang
	 */
	public List<ShipmentBatchDetail> getALl(Store storeID) {

		//List<ShipmentBatchDetail> list = shipmentBacthDetailJPA.findByStore(storeID);
		return null;

	}

	public void insert(List<ShipmentBatchDetail> shipBatchDetails) {
		for (ShipmentBatchDetail shipmentBatchDetail : shipBatchDetails) {
			shipmentBacthDetailJPA.save(shipmentBatchDetail);
		}

	}

	public void delete(ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailJPA.delete(shipmentBatchDetail);
	}

	public void deleteWithShelfs(List<ShipmentBatchDetail> list) {
		for (ShipmentBatchDetail shipmentBatchDetail : list) {
			shipmentBacthDetailJPA.delete(shipmentBatchDetail);
		}

	}

	public void setStatus(String productID, String shipmentBatchID) {
		//shipmentBacthDetailJPA.setStatus(productID, shipmentBatchID);
	}
}
