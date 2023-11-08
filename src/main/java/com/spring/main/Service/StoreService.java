package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.StoreJPA;
import com.spring.main.model.Store;

@Service
public class StoreService {
	@Autowired
	StoreJPA storeJPA;

	public Store findByID(Integer storeID) {
		return storeJPA.findById(storeID).get();
	}
	
	public Store findByAddress(String address) {
		return storeJPA.findByAddress(address);
	}
}
