package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.BillService;
import com.spring.main.model.Bill;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class BillAPI {
@Autowired
BillService billService;
@PostMapping("bill/save")
private void createBill(@RequestBody Bill bill) {
	billService.save(bill);
}
}
