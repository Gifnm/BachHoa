package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.StoreService;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class StoreAPI {
	@Autowired
	StoreService storeService;
	
	@GetMapping("store/findByID/{storeID}")
	public Store findByID(@PathVariable("storeID") Integer storeID) {
		return storeService.findByID(storeID);
	}
	
	@GetMapping("store/findByAddress/{address}")
	public Store findByAddress(@PathVariable("address") String address) {
		return storeService.findByAddress(address);
	}
	
	@GetMapping("store/findByAddress/{storeID}")
	public Store findByStoreID(@PathVariable("storeID") Integer storeID) {
		return storeService.findByID(storeID);
	}
}
