package com.spring.main.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("bill/all/{storeID}")
	public Page<Bill> getAll(@PathVariable("storeID") Integer storeID, @RequestParam("index") Optional<Integer> index) {
		Pageable page = PageRequest.of(index.orElse(0), 8);
		return billService.findAllByStoreID(storeID, page);
	}

	@GetMapping("bill/getBillID/{storeID}")
	public List<String> getBillID(@PathVariable("storeID") Integer storeID) {
		return billService.getBillID(storeID);

	}

	@GetMapping("bill/findBill/{BillID}")
	public Bill getOne(@PathVariable("BillID") String id) {
		return billService.findByID(id);
	}

	@GetMapping("bill/searchBetween/{fromDate}/{toDate}")
	public Page<Bill> searchTimeCreate(@PathVariable("fromDate") Timestamp fromDate,
			@PathVariable("toDate") Timestamp toDate, @RequestParam Optional<Integer> index,
			@RequestParam("store-id") Integer storeID) {
		Pageable page = PageRequest.of(index.orElse(0), 8);
		return billService.searchBetween(fromDate, toDate, storeID, page);
	}

	@GetMapping("bill/findByEmployeeAndDate/{employeeID}/{fromDate}/{toDate}")
	public List<Bill> findByEmployeeAndDate(@PathVariable("employeeID") Integer employeeID,
			@PathVariable("fromDate") Timestamp fromDate, @PathVariable("toDate") Timestamp toDate) {
		return billService.findByEmployeeAndDate(employeeID, fromDate, toDate);
	}

	@GetMapping("bills")
	public List<Bill> getBillsBetween(@RequestParam("from-date") String fromDate,
			@RequestParam("to-date") String toDate, @RequestParam("store-id") int storeId) {
		return billService.findAllByTimeCreateBetween(fromDate, toDate, storeId);
	}

	@PutMapping("bill/update")
	public void update(@RequestBody Bill bill) {
		billService.save(bill);
	}

	@DeleteMapping("bill/delete/{billID}")
	public void delete(@PathVariable("billID") String id) {
		billService.delete(id);
	}
}
