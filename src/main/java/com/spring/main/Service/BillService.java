package com.spring.main.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Bill> searchBetween(Timestamp fromDate, Timestamp toDate, Pageable page) {
		return billJPA.SearchBetween2Date(fromDate, toDate, page);
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

	public List<Bill> findAllByTimeCreateBetween(String fromDate, String toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp stDate = null;
		Timestamp enDate = null;
		try {
			stDate = new Timestamp(dateFormat.parse(fromDate).getTime());
			enDate = new Timestamp(dateFormat.parse(toDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return billJPA.findAllByTimeCreateBetween(stDate, enDate);
	}
}
