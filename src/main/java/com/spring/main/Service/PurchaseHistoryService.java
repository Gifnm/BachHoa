package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.PurchaseHistoryJPA;
import com.spring.main.model.PurchaseHistory;

@Service
public class PurchaseHistoryService {
@Autowired
PurchaseHistoryJPA purchaseHistoryJPA;

public void save(PurchaseHistory purchaseHistory) {
	purchaseHistoryJPA.save(purchaseHistory);
}
}
