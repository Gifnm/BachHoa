package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.PaymentDetailJPA;
import com.spring.main.model.PaymentDetail;
import com.spring.main.model.PaymentHistory;

@Service
public class PaymentDetailService {
	@Autowired
	PaymentDetailJPA paymentDetailJPA;

	public PaymentDetail save(PaymentDetail paymentDetail) {
		paymentDetailJPA.save(paymentDetail);
		return paymentDetail;
	}

	public PaymentHistory findByPaymentID(Integer id) {
		return paymentDetailJPA.findByPaymentID(id);
	}

	public List<PaymentDetail> findAll() {
		return paymentDetailJPA.findAll();
	}
	
	public PaymentDetail findByID(Integer id) {
		return paymentDetailJPA.findById(id).get();
	}

}
