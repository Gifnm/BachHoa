package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.model.PurchaseHistory;
import com.spring.main.model.PurchaseHistoryJPA;
@Service
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
