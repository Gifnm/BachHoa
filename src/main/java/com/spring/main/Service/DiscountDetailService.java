package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DiscountDetailJPA;
import com.spring.main.model.DiscountDetails;

@Service
public class DiscountDetailService {
	@Autowired
	DiscountDetailJPA discountDetaileJPA;

	public DiscountDetails findByProductIDAndStoreID(String productID, Integer storeID) {
		return discountDetaileJPA.findByProductIDAndStoreID(productID, storeID);
	}
}
