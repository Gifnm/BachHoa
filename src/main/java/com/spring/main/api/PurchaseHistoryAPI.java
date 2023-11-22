package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.PurchaseHistoryService;
import com.spring.main.model.PurchaseHistory;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/purchasehistory/")
public class PurchaseHistoryAPI {
	@Autowired
	PurchaseHistoryService purchaseHistoryService;

	@GetMapping("/{deliveryId}")
	public ResponseEntity<List<PurchaseHistory>> getByDeliveryId(@PathVariable("deliveryId") String deliveryId) {
		List<PurchaseHistory> list = purchaseHistoryService.getByDeliveryId(deliveryId);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}
}
