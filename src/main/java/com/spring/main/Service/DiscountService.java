package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DiscountJPA;
import com.spring.main.model.Discount;

@Service
public class DiscountService {
	@Autowired
	DiscountJPA discountJPA;

	public Discount get(String disID) {
		return discountJPA.findById(disID).get();
	}
}
