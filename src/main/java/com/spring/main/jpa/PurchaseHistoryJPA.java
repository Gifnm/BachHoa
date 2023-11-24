package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.PurchaseHistory;

public interface PurchaseHistoryJPA extends JpaRepository<PurchaseHistory, String> {
    @Query(value = "Select * from purchase_history where id  = ?1", nativeQuery = true)
    List<PurchaseHistory> getByDeliveryId(String deliveryId);
}
