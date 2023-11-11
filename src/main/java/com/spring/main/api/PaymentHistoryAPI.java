package com.spring.main.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<PaymentHistory> findByEmployee(@PathVariable("employeeID") Integer employeeID, @RequestParam Optional<Integer> index){
		Pageable page = PageRequest.of(index.orElse(0), 8);
		return paymentHistoryService.findByEmployee(employeeID, page);
	}
	
	@GetMapping("paymentHistory/getAll")
	public List<PaymentHistory> getAll(){
		return paymentHistoryService.getAll();
	}
	
	@GetMapping("paymentHistory/findByDate/{fromDate}/{toDate}")
	public Page<PaymentHistory> findByDate(@PathVariable("fromDate") Timestamp fromDate, @PathVariable("toDate") Timestamp toDate,  @RequestParam Optional<Integer> index){
		Pageable page = PageRequest.of(index.orElse(0), 8);
		return paymentHistoryService.findByDate(fromDate, toDate, page);
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
