package com.spring.main.Service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.PaymentHistoryJPA;
import com.spring.main.model.PaymentHistory;

@Service
public class PaymentHistoryService {
	@Autowired
	PaymentHistoryJPA paymentHistoryJPA;

	public void save(PaymentHistory paymentHistory) {
		paymentHistoryJPA.save(paymentHistory);
	}
	
	public Page<PaymentHistory> getPayment(Pageable page) {
		return paymentHistoryJPA.getPayment(page);
	}

	public Page<PaymentHistory> findByEmployee(Integer employeeID, Pageable page) {
		return paymentHistoryJPA.findByEmployee(employeeID, page);
	}

	public Page<PaymentHistory> findByDate(Timestamp fromeDate, Timestamp toDate, Pageable page) {
		return paymentHistoryJPA.findByDate(fromeDate, toDate, page);
	}

	public Page<PaymentHistory> getAllPayment(Integer storeID, Pageable page) {
		return paymentHistoryJPA.getAllPayment(storeID, page);
	}

	public PaymentHistory findByID(Integer id) {
		return paymentHistoryJPA.findById(id).get();
	}
}
