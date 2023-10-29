package com.spring.main.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillJPA;
import com.spring.main.model.Bill;

@Service
public class BillService {
	@Autowired
	BillJPA billJPA;

	public Bill save(Bill bill) {
		billJPA.save(bill);
		return bill;
	}
	
	public Bill findOneBill(String billID) {
		return billJPA.findById(billID).get();
	}
	
	public List<Bill> searchBetween(Date fromDate, Date toDate) {
		return billJPA.SearchBetween2Date(fromDate, toDate);
	}
	
	public List<Bill> findAll() {
		return billJPA.findAll();
	}
	
	public void delete(String billID) {
		billJPA.deleteById(billID);
	}

	public List<String> getBillID() {
		return billJPA.getBillID();
	}

}
