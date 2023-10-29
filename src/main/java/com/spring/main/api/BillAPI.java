package com.spring.main.api;

import java.sql.Timestamp;
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

import com.spring.main.Service.BillService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.Bill;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class BillAPI {
	@Autowired
	BillService billService;
	@Autowired
	ProductService productService;

	@PostMapping("bill/save")
	private void createBill(@RequestBody Bill bill) {
		billService.save(bill);
	}
	
	@GetMapping("bill/all")
	public List<Bill> getAll() {
		return billService.findAll();
	}
	
	@GetMapping("bill/getBillID")
	public List<String> getBillID() {
		return billService.getBillID();

	}

	@GetMapping("bill/findBill/{BillID}")
	public Bill getOne(@PathVariable("BillID") String id) {
		return billService.findByID(id);
	}
	
	@GetMapping("bill/searchBetween/{fromDate}/{toDate}")
	public List<Bill> searchTimeCreate(@PathVariable("fromDate") Timestamp fromDate, @PathVariable("toDate") Timestamp toDate) {
		return billService.searchBetween(fromDate, toDate);
	}
	 
	@PutMapping("bill/update/{BillID}")
	public void update(@PathVariable("BillID") String id, @RequestBody Bill bill) {
		billService.save(bill);
	}
	
	@DeleteMapping("bill/delete/{BillID}")
	public void delete(@PathVariable("BillID") String id) {
		billService.delete(id);
	}
}
