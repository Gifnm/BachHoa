package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DetailedDeliveryNoteService;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/detaildeliverynote/")
public class DetailedDeliveryNoteAPI {
	@Autowired
	DetailedDeliveryNoteService deliveryNoteService;

	@GetMapping("getall")
	private ResponseEntity<List<DetailedDeliveryNote>> getAll(@RequestPart("deliverNote") DeliveryNote deliveryNote) {
		List<DetailedDeliveryNote> list = deliveryNoteService.getall(deliveryNote);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(list);

	}
}
