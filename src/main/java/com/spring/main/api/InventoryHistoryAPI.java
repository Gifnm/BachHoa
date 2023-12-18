package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Timestamp;
import com.spring.main.Service.InventoryHistoryService;
import com.spring.main.model.InventoryHistory;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api/inventory/")
public class InventoryHistoryAPI {
// Tim doi tuong InventoryHistoryService
	@Autowired
	InventoryHistoryService inventoryHistoryService;

	@PostMapping("insert")
	private ResponseEntity<Boolean> insert(@RequestBody() InventoryHistory inventoryHistory) {
		System.out.println("insert");
		long time = System.currentTimeMillis();
		inventoryHistory.setCountingTime(new java.sql.Timestamp(time));
		inventoryHistory.setInHisID(String.valueOf(time));
		if (inventoryHistoryService.insert(inventoryHistory)) {

			return ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(false);

		}
	}

	@GetMapping("getByProductIDAndStoreID/{productID}/{storeID}")
	private ResponseEntity<List<InventoryHistory>> getByProductIDAndStoreID(@PathVariable("productID") String productID,
			@PathVariable("storeID") int storeID) {
		System.out.println("getByProductIDAndStoreID");
		List<InventoryHistory> list = inventoryHistoryService.getByProductIDAndStoreID(productID, storeID);
		if (list != null) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

	@GetMapping("getByStoreIDAndDate")
	private ResponseEntity<List<InventoryHistory>> getByStoreIDAndDate(@PathVariable("storeID") int storeID,
			@PathVariable("date") Timestamp date) {
		List<InventoryHistory> list = inventoryHistoryService.getByStoreIDAndDate(storeID, date);
		if (list != null) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}
	}
}
