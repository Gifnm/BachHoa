package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthJPA;
import com.spring.main.model.ShipmentBatch;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthService {
	@Autowired
	ShipmentBacthJPA shipmentBacthJPA;

	// 1. Lay danh sach ShipmentBatch theo cua hang
	public List<ShipmentBatch> findByStoreID(Store store) {
		List<ShipmentBatch> list = shipmentBacthJPA.findByStore(store);
		return list;
	}

	// 2. Tao ShipmentBatch
	public void insert(ShipmentBatch shipmentBatch) {
		shipmentBacthJPA.save(shipmentBatch);
	}

	// 3. Chuyen trang thai hoan thanh cho ShipmentBatch
	public boolean setFinish(String shiBatID) {
		return shipmentBacthJPA.setFinish(shiBatID);
	}
}
