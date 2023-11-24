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

	public Page<Bill> searchBetween(Timestamp fromDate, Timestamp toDate, Pageable page, int storeId) {
		return billJPA.SearchBetween2Date(storeId, fromDate, toDate, page);
	}

	public List<Bill> findByEmployeeAndDate(Integer employeeID, Timestamp fromDate, Timestamp toDate) {
		return billJPA.findByEmployeeAndDate(employeeID, fromDate, toDate);
	}

	public List<Bill> findAllByStoreID(Integer storeID) {
		return billJPA.findAllByStoreID(storeID);
	}

	public void delete(String billID) {
		billJPA.deleteById(billID);
	}

	public List<String> getBillID(Integer storeID) {
		return billJPA.getBillID(storeID);
	}

	public List<Bill> findAllByTimeCreateBetween(String fromDate, String toDate, int storeId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp stDate = null;
		Timestamp enDate = null;
		try {
			stDate = new Timestamp(dateFormat.parse(fromDate).getTime());
			enDate = new Timestamp(dateFormat.parse(toDate).getTime());
			enDate.setHours(23);
			enDate.setMinutes(59);
			enDate.setSeconds(59);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return billJPA.findAllByTimeCreateBetween(stDate, enDate, storeId);
	}

	public Bill findOneByBillIdAndStoreId(String billId, int storeId) {
		System.out.println(billJPA.findOneByBillIdAndStoreId(billId, storeId));
		return billJPA.findOneByBillIdAndStoreId(billId, storeId);
	}
}
