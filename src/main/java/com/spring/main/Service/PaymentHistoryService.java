package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<PaymentHistory> findByEmployee(Integer employeeID) {
		return paymentHistoryJPA.findByEmployee(employeeID);
	}

	public List<PaymentHistory> getAll() {
		return paymentHistoryJPA.findAll();
	}
	
	public PaymentHistory findByID(Integer id) {
		return paymentHistoryJPA.findById(id).get();
	}
}
