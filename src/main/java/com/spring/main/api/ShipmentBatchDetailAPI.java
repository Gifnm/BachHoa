package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.ShipmentBacthDetailService;
import com.spring.main.model.ShipmentBatchDetail;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/shipBatDet/")
public class ShipmentBatchDetailAPI {
	@Autowired
	ShipmentBacthDetailService shipmentBacthDetailService;

	/*
	 * 1. Chuyen trang thai sau khi cham hang - Tham so: Ma san pham, ma ban giao ca
	 * - Dung khi:
	 */
	@PutMapping("setStatus")
	private void setStatus(@PathVariable("shipID") String shiBatID, @PathVariable("productID") String productID) {
		shipmentBacthDetailService.setStatus(productID, shiBatID);
	}

	/*
	 * 2. Xoa danh sach cham hang - Tham so: ma ban giao ca (shiBatID), ma san pham
	 * - Dung khi:
	 */
	@DeleteMapping("deleteByShelf")
	private void deleteWithShelf(@RequestPart("listByShelf") List<ShipmentBatchDetail> list) {
		shipmentBacthDetailService.deleteWithShelfs(list);

	}
}
