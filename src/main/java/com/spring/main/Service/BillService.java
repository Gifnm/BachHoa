package com.spring.main.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillJPA;
import com.spring.main.model.Bill;

@Service
public class BillService {
	@Autowired
	BillJPA billJPA;

	public void save(Bill bill) {
		billJPA.save(bill);
	}

	public Bill findByID(String billID) {
		return billJPA.findById(billID).get();
	}

	public List<Bill> searchBetween(Timestamp fromDate, Timestamp toDate) {
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
