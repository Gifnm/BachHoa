package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.PaymentDetailService;
import com.spring.main.model.PaymentDetail;

@RestController
@CrossOrigin("*")
@RequestMapping("/bachhoa/api/")
public class PaymentDetailAPI {
	@Autowired
	PaymentDetailService paymentDetailService;

	@GetMapping("paymentDetail/findByPaymentID/{id}")
	public PaymentDetail getPaymentDetail(@PathVariable("id") Integer id) {
		return paymentDetailService.findByPaymentID(id);
	}
	
	@GetMapping("paymentDetail/findByID/{id}")
	public PaymentDetail findByID(@PathVariable("id") Integer id) {
		return paymentDetailService.findByID(id);
	}
	
	@PostMapping("paymentDetail/create")
	public PaymentDetail create(@RequestBody PaymentDetail paymentDetail) {
		paymentDetailService.save(paymentDetail);
		return paymentDetail;
	}
	
	@PutMapping("paymentDetail/update")
	public PaymentDetail update(@RequestBody PaymentDetail paymentDetail) {
		paymentDetailService.save(paymentDetail);
		return paymentDetail;
	}
}
