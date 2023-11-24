package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.StoreJPA;
import com.spring.main.model.Store;

@Service
public class StoreService {
	@Autowired
	StoreJPA storeJPA;
	
	public List<Store> findAll() {
		List<Store> list = storeJPA.findAll();
		return list;
	}
	
	public Store insert(Store store) {
		return storeJPA.save(store);
	}

	public Store findByID(Integer storeID) {
		return storeJPA.findById(storeID).get();
	}
	
	public Store findByAddress(String address) {
		return storeJPA.findByAddress(address);
	}
}
