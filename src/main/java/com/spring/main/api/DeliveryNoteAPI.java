package com.spring.main.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.EntityDTO.DeliveryNoteDTO;
import com.spring.main.EntityDTO.DeliveryNoteWithTotalAmountDTO;
import com.spring.main.EntityDTO.DetailedDeliveryNoteDTO;
import com.spring.main.Service.DeliveryNoteService;
import com.spring.main.model.DeliveryNote;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/deliverynotapi/")
public class DeliveryNoteAPI {
	@Autowired
	DeliveryNoteService deliveryNoteService;

	// Getll
	@GetMapping("getall/{storeID}")
	private ResponseEntity<List<DeliveryNote>> getAll(@PathVariable("storeID") int storeID) {
		List<DeliveryNote> list = deliveryNoteService.getAll(storeID);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}

	@GetMapping("with-total-amount/{storeId}")
	public ResponseEntity<List<DeliveryNoteWithTotalAmountDTO>> getDeliveryNotesWithTotalAmount(
			@PathVariable("storeId") int storeID) {
		List<DeliveryNoteWithTotalAmountDTO> result = deliveryNoteService.getDeliveryNotesWithTotalAmount(storeID);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("create")
	public ResponseEntity<Map<String, String>> createDeliveryNote(@RequestBody DeliveryNoteDTO deliveryNoteDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			deliveryNoteService.createDeliveryNote(deliveryNoteDTO);
			response.put("message", "Delivery note created successfully");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{deliveryNoteId}")
	public ResponseEntity<Map<String, String>> updateDeliveryNote(@PathVariable String deliveryNoteId,
			@RequestBody List<DetailedDeliveryNoteDTO> detailDeliveryNoteDTOs) {
		Map<String, String> response = new HashMap<>();
		try {
			deliveryNoteService.updateDeliveryNote(deliveryNoteId, detailDeliveryNoteDTOs);
			response.put("message", "Delivery note updated successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{deliveryNoteId}")
	public ResponseEntity<Map<String, String>> removeDeliveryNoteAndDetails(@PathVariable String deliveryNoteId) {
		Map<String, String> response = new HashMap<>();
		try {
			deliveryNoteService.deleteDeliveryNoteWithDetails(deliveryNoteId);
			response.put("message", "Delivery note created successfully");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
