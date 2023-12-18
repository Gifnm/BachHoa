package com.spring.main.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<DiscountDetails> findByStoreID(Integer storeID, Pageable page) {
		return discountDetaileJPA.findByStoreID(storeID, page);
	}
	
	public Page<DiscountDetails> findByDate(Integer storeID,  Date startTime, Date endTime, Pageable page) {
		return discountDetaileJPA.findByDate(storeID, startTime, endTime, page);
	}
	
	public List<DiscountDetails> findByStoreIDAndProductID(Integer storeID, String productID) {
		return discountDetaileJPA.findByStoreIDAndProductID(storeID, productID);
	}
	
	public DiscountDetails create(DiscountDetails discount) {
		return discountDetaileJPA.save(discount);
	}

	public DiscountDetails stop(DiscountDetails discount) {
		return discountDetaileJPA.save(discount);
	}
	
	public void delete(DiscountDetails discount) {
		discountDetaileJPA.delete(discount);
	}
	
	public void update(Date startTime, Date endTime, String productID, Integer storeID) {
		discountDetaileJPA.update(startTime, endTime, productID, storeID);
	}
	public DiscountDetails findByProductIDAndStoreID(String productID, Integer storeID) {
		return discountDetaileJPA.findByProductIDAndStoreID(productID, storeID);}
}
