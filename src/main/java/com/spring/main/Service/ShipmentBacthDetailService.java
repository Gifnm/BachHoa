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

	/**
	 * Lay danh sach dot cham hang chi tiet
	 * 
	 * @param shipmentBatchID Ma dot cham hang
	 */
	public List<ShipmentBatchDetail> getALl(String shipmentBatchID) {
		List<ShipmentBatchDetail> list = shipmentBacthDetailJPA.getByshiBatID(shipmentBatchID);
		return list;

	}

	/**
	 * Luu danh sach cham hang chi tiet
	 * 
	 * @param shipBatchDetails Danh sach cham hang chi tiet
	 */
	public void insert(List<ShipmentBatchDetail> shipBatchDetails) {
		for (ShipmentBatchDetail shipmentBatchDetail : shipBatchDetails) {
			shipmentBacthDetailJPA.save(shipmentBatchDetail);
		}

	}

	/**
	 * Xoa cham hang chi tiet khoi co so du lieu Dung khi bao het hang
	 * 
	 * @param shipmentBatchDetail Object Cham hang chi tiet
	 */
	public void delete(ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailJPA.delete(shipmentBatchDetail);
	}

	/**
	 * Xoa mot danh sach cham hang chi tiet Dung khi hoan tat cham hang cua mot ke
	 * trung bay
	 * 
	 * @param list Danh sach cham hang chi tiet
	 * 
	 */
	public void deleteWithShelfs(List<ShipmentBatchDetail> list) {
		for (ShipmentBatchDetail shipmentBatchDetail : list) {
			shipmentBacthDetailJPA.delete(shipmentBatchDetail);
		}

	}

	/**
	 * Tao mot Cham hang chi tiet
	 * 
	 * @param shipmentBatchDetail Cham hang chi tiet
	 */
	public void insert(ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailJPA.save(shipmentBatchDetail);
	}

}
