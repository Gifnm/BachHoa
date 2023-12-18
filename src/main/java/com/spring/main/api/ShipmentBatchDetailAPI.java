package com.spring.main.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.EntityDTO.ShipmentBacthDTO;
import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ShipmentBacthDetailService;
import com.spring.main.Service.ShipmentBacthService;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.ShipmentBatch;
import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/shipbatdet/")
public class ShipmentBatchDetailAPI {
	@Autowired
	ShipmentBacthDetailService shipmentBacthDetailService;
	@Autowired
	ShipmentBacthService shipmentBacthService;
	@Autowired
	ProductPosionService productPosionService;

	/*
	 * 1. Chuyen trang thai sau khi cham hang - Tham so: Ma san pham, ma ban giao ca
	 * - Dung khi:
	 */
	@PutMapping("setStatus")
	private void setStatus(@RequestPart("ShipmentBatchDetail") ShipmentBatchDetail shipmentBatchDetail) {
		shipmentBacthDetailService.insert(shipmentBatchDetail);
	}

	/*
	 * 2. Xoa danh sach cham hang - Tham so: ma ban giao ca (shiBatID), ma san pham
	 * - Dung khi:
	 */
	@DeleteMapping("deleteByShelf")
	private void deleteWithShelf(@RequestPart("listByShelf") List<ShipmentBatchDetail> list) {
		shipmentBacthDetailService.deleteWithShelfs(list);
	}

	// 3. Lay danh sach ShipmentBatchDetail theo shiBatID
	@GetMapping("getByshiBatID/{shiBatID}")
	private List<ShipmentBacthDTO> getAll(@PathVariable("shiBatID") String shiBatID) {
		List<ShipmentBatchDetail> list = shipmentBacthDetailService.getALl(shiBatID);
		List<ShipmentBacthDTO> listShipmentBatch = new ArrayList<>();
		for (ShipmentBatchDetail shipmentBatchDetail : list) {
			ProductPositioning productPositioning = productPosionService.getByIDAndStoreID(
					shipmentBatchDetail.getProduct().getProductID(),
					shipmentBatchDetail.getProduct().getStore().getStoreID());
			listShipmentBatch.add(new ShipmentBacthDTO(shipmentBatchDetail, productPositioning));
		}
		return listShipmentBatch;
	}

	// 4. Lay danh sach ShipmentBatchDetail theo cua hang
	@GetMapping("check/{productID}/{storeID}")
	private ResponseEntity<ProductPositioning> checkProduct(@PathVariable("productID") String productID,
			@PathVariable("storeID") int storeID) {
		System.out.println("checks");
		ProductPositioning productPositioning = productPosionService.getByIDAndStoreID(productID, storeID);
		ShipmentBatchDetail shipmentBatchDetail = new ShipmentBatchDetail();
		if (productPositioning == null) {
			
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		} else {
			System.out.println(productPositioning.getProduct().getImage());
			return ResponseEntity.status(HttpStatus.OK).body(productPositioning);
		}

	}

}
