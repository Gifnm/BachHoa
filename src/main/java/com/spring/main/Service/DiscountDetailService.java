package com.spring.main.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DiscountDetailJPA;
import com.spring.main.model.DiscountDetails;

@Service
public class DiscountDetailService {
	@Autowired
	DiscountDetailJPA discountDetaileJPA;

	public DiscountDetails findDiscountIsActive(String productID, Integer storeID) {
		return discountDetaileJPA.findDiscountIsActive(productID, storeID);
	}
	
	public List<DiscountDetails> findByStoreID(Integer storeID) {
		return discountDetaileJPA.findByStoreID(storeID);
	}
	
	public List<DiscountDetails> findByDate(Integer storeID,  Date startTime, Date endTime) {
		return discountDetaileJPA.findByDate(storeID, startTime, endTime);
	}
	
	public List<DiscountDetails> findByStoreIDAndProductID(Integer storeID, String productID) {
		return discountDetaileJPA.findByStoreIDAndProductID(storeID, productID);
	}
	
	public DiscountDetails create(DiscountDetails discount) {
		return discountDetaileJPA.save(discount);
	}
	
	public void delete(DiscountDetails discount) {
		discountDetaileJPA.delete(discount);
	}
	
	public void update(String disID, Date startTime, Date endTime, String productID, Integer storeID) {
		discountDetaileJPA.update(disID, startTime, endTime, productID, storeID);
	}
}
