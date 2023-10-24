package com.spring.main.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBatchDetailJPA;
import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthDetailService {
	@Autowired
	ShipmentBatchDetailJPA shipmentBacthDetailJPA;

	// Cac phuong thuc
	// 1. Lay danh sach cham hang chi tiet theo shiBatID
	// 2. Tao ShipmentBatchDetail
	// 3. Xoa ShipmentBatchDetaile
	// 4. Xoa danh sach ShipmentBatchDetail theo shiBatID
	// 5. Them hoac cap nhat 1 ShipmentBatchDetail

	/*
	 * 1. Lay danh sach cham hang chi tiet theo shiBatID
	 * 
	 */
	public List<ShipmentBatchDetail> getALl(String shipmentBatchID) {
		List<ShipmentBatchDetail> list = shipmentBacthDetailJPA.getAllByShiBatID(shipmentBatchID);
		return list;

	}

	/*
	 * 2. Tao ShipmentBatchDetail
	 * 
	 */
	public void insert(List<ShipmentBatchDetail> shipBatchDetails) {
		for (ShipmentBatchDetail shipmentBatchDetail : shipBatchDetails) {
			shipmentBacthDetailJPA.save(shipmentBatchDetail);
		}

	}

	/*
	 * 3. Xoa ShipmentBatchDetail
	 * 
	 */
	public void delete(ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailJPA.delete(shipmentBatchDetail);
	}

	/*
	 * 4. Xoa mot danh sach ShipmentBacthDetail theo shiBatID
	 * 
	 */
	public void deleteWithShelfs(List<ShipmentBatchDetail> list) {
		for (ShipmentBatchDetail shipmentBatchDetail : list) {
			shipmentBacthDetailJPA.delete(shipmentBatchDetail);
		}

	}

	// 5. Them hoac cap nhat 1 ShipmentBatchDetail
	public void insert(ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailJPA.save(shipmentBatchDetail);
	}

}
