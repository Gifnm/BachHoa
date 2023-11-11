package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.PurchaseHistory;

public interface PurchaseHistoryJPA  extends JpaRepository<PurchaseHistory, String>{

}
