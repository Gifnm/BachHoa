package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.PaymentHistoryService;
import com.spring.main.model.PaymentHistory;

@RestController
@CrossOrigin("*")
@RequestMapping("/bachhoa/api/")
public class PaymentHistoryAPI {
	@Autowired
	PaymentHistoryService paymentHistoryService;
	
	@GetMapping("paymentHistory/findByEmployee/{employeeID}")
	public List<PaymentHistory> findByEmployee(@PathVariable("employeeID") Integer employeeID){
		return paymentHistoryService.findByEmployee(employeeID);
	}
	
	@GetMapping("paymentHistory/getAll")
	public List<PaymentHistory> getAll(){
		return paymentHistoryService.getAll();
	}
	
	@GetMapping("paymentHistory/findByID/{id}")
	public PaymentHistory findByID(@PathVariable("id") Integer id){
		return paymentHistoryService.findByID(id);
	}
	
	@PostMapping("paymentHistory/create")
	public PaymentHistory create(@RequestBody PaymentHistory paymentHistory) {
		paymentHistoryService.save(paymentHistory);
		return paymentHistory;
	}
	
	@PutMapping("paymentHistory/update")
	public PaymentHistory update(@RequestBody PaymentHistory paymentHistory) {
		paymentHistoryService.save(paymentHistory);
		return paymentHistory;
	}
}
