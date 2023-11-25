package com.spring.main.api;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DiscountDetailService;
import com.spring.main.Service.DiscountService;
import com.spring.main.model.Discount;
import com.spring.main.model.DiscountDetails;

@CrossOrigin("*")
@RequestMapping("/bachhoa/api")
@RestController
public class DiscountAPI {
	@Autowired
	DiscountDetailService discountDetailService;
	@Autowired
	DiscountService discountService;

	@GetMapping("discount/findDiscountIsActive/{productID}/{storeID}")
	public DiscountDetails findByProductIDAndStoreID(@PathVariable String productID, @PathVariable Integer storeID) {
		return discountDetailService.findDiscountIsActive(productID, storeID);
	}

	@GetMapping("discount/findByStoreID/{storeID}")
	public List<DiscountDetails> findByStoreID(@PathVariable("storeID") Integer storeID) {
		return discountDetailService.findByStoreID(storeID);
	}
	
	@GetMapping("discount/findByDate/{storeID}/{startTime}/{endTime}")
	public List<DiscountDetails> findByDate(@PathVariable("storeID") Integer storeID, @PathVariable("startTime") Date startTime,
			@PathVariable("endTime") Date endTime) {
		return discountDetailService.findByDate(storeID, startTime, endTime);
	}
	
	@GetMapping("discount/findByStoreIDAndProductID/{storeID}/{productID}")
	public List<DiscountDetails> findByStoreIDAndProductID(@PathVariable("storeID") Integer storeID, @PathVariable("productID") String productID) {
		return discountDetailService.findByStoreIDAndProductID(storeID, productID);
	}

	@GetMapping("discount/getDiscount/{disID}")
	public Discount getDiscount(@PathVariable("disID") String disID) {
		return discountService.get(disID);
	}

	@PostMapping("discount/create")
	public DiscountDetails create(@RequestBody DiscountDetails discount) {
		return discountDetailService.create(discount);
	}

	@PutMapping("discount/update/{startTime}/{endTime}/{productID}/{storeID}")
	public void update(@PathVariable("startTime") Date startTime,
			@PathVariable("endTime") Date endTime, @PathVariable("productID") String productID,
			@PathVariable("storeID") Integer storeID) {
		discountDetailService.update(startTime, endTime, productID, storeID);
	}

	@DeleteMapping("discount/delete/{productID}/{storeID}")
	public void delete(@PathVariable("productID") String productID, @PathVariable("storeID") Integer storeID) {
		DiscountDetails discount = discountDetailService.findByStoreIDAndProductID(storeID, productID).get(0);
		discountDetailService.delete(discount);
	}
}
