package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.main.jpa.PaymentDetailJPA;
import com.spring.main.model.PaymentHistory;

@Service
public class PaymentDetailService {
	@Autowired
	PaymentDetailJPA paymentDetailJPA;

	public PaymentHistory findByPaymentID(Integer id) {
		return paymentDetailJPA.findByPaymentID(id);
	}
}
