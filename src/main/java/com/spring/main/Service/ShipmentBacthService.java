package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthJPA;
import com.spring.main.model.ShipmentBatch;

@Service
public class ShipmentBacthService {
	@Autowired
	ShipmentBacthJPA shipmentBacthJPA;
	@Autowired
	ProductPosionService productPosionService;

	/**
	 * Lay danh sach dot cham hang
	 * 
	 * @param storeID ma cua hang
	 */
	public List<ShipmentBatch> findByStoreID(int storeID) {
		List<ShipmentBatch> list = shipmentBacthJPA.findByStore(storeID);
		return list;
	}

	/**
	 * Luu dot cham hang
	 * 
	 * @param object Dot cham hang
	 */
	public void insert(ShipmentBatch shipmentBatch) {
		shipmentBacthJPA.save(shipmentBatch);
	}

	/**
	 * Chuyen doi trang thai dot cham hang sau khi hoan tat
	 * 
	 * @param Ma dot cham hang
	 */
	public boolean setFinish(String shiBatID) {
		return shipmentBacthJPA.setFinish(shiBatID);
	}
	// 4. Kiem tra san pham

}
