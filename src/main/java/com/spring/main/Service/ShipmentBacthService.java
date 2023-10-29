package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.main.jpa.ShipmentBacthJPA;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.ShipmentBatch;
import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthService {
	@Autowired
	ShipmentBacthJPA shipmentBacthJPA;
	@Autowired
	ProductPosionService productPosionService;

	// 1. Lay danh sach ShipmentBatch theo cua hang
	public List<ShipmentBatch> findByStoreID(int storeID) {
		List<ShipmentBatch> list = shipmentBacthJPA.findByStore(storeID);
		System.out.println("li" + storeID);
		System.out.println("li2 " + list.size());
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
	//4. Kiem tra san pham

}
