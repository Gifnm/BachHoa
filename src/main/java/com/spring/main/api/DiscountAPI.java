package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DiscountDetailService;
import com.spring.main.model.DiscountDetails;

@CrossOrigin("*")
@RestController
public class DiscountAPI {
@Autowired
DiscountDetailService discountDetailService;

@GetMapping("discount/findByProductIDAndStoreID/{productID}/{storeID}")
public DiscountDetails findByProductIDAndStoreID(@PathVariable String productID, @PathVariable Integer storeID) {
	return discountDetailService.findByProductIDAndStoreID(productID, storeID);
}
}
