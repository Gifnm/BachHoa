package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DeliveryNoteService;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/deliverynotapi/")
public class DeliveryNoteAPI {
	@Autowired
	DeliveryNoteService deliveryNoteService;

	// Getll
	@GetMapping("getall/{storeID}")
	private ResponseEntity<List<DeliveryNote>> getAll(@PathVariable("storeID") int  storeID) {
		List<DeliveryNote> list = deliveryNoteService.getAll(storeID);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			System.out.println("Danh sach phieu nhap: "+ list.size());
			return ResponseEntity.status(HttpStatus.OK).body(list);}
	}

}
