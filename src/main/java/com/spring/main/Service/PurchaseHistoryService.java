package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.main.model.PurchaseHistory;
import com.spring.main.model.PurchaseHistoryJPA;

public class PurchaseHistoryService {
	@Autowired
	PurchaseHistoryJPA purchaseHistoryJPA;

	/**
	 * Luu lich su nhap hang
	 */
	public void insert(PurchaseHistory purchaseHistory) {
		purchaseHistoryJPA.save(purchaseHistory);
	}
}
