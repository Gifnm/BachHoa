package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.PaymentDetail;

public interface PaymentDetailJPA extends JpaRepository<PaymentDetail, Integer>{

	@Query("select o from PaymentDetail o where o.paymentHistory.id = ?1")
	PaymentDetail findByPaymentID(Integer id);
}
