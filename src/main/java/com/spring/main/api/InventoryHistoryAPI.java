package com.spring.main.api;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.InventoryHistoryService;
import com.spring.main.model.InventoryHistory;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class InventoryHistoryAPI {
	@Autowired
	InventoryHistoryService inventoryHistoryService;

	@GetMapping("inventoryHistory/findByDate/{storeID}/{fromDate}/{toDate}")
	public Page<InventoryHistory> findByDate(@PathVariable("fromDate") Timestamp fromDate,
			@PathVariable("toDate") Timestamp toDate, @PathVariable("storeID") Integer storeID,
			@RequestParam Optional<Integer> index) {
		Pageable page = PageRequest.of(index.orElse(0), 8);
		return inventoryHistoryService.findByDate(fromDate, toDate, storeID, page);
	}
}
