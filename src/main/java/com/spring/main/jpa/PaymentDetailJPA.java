package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.PaymentDetail;
import com.spring.main.model.PaymentHistory;

public interface PaymentDetailJPA extends JpaRepository<PaymentDetail, Integer>{

	@Query("select o from PaymentHistory o where o.id = ?1")
	PaymentHistory findByPaymentID(Integer id);
}
