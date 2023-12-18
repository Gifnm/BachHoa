package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.google.protobuf.Timestamp;
import com.spring.main.model.InventoryHistory;

public interface InventoryHistoryJPA extends JpaRepository<InventoryHistory, String> {

	/**
	 * Lay danh sach lich su kiem ke theo ma san pham
	 */
	@Query("SELECT o FROM InventoryHistory o WHERE o.product.productID =:productID AND o.store.storeID =:storeID")
	List<InventoryHistory> getByProductIDAndstoreID(String productID, int storeID);

	/**
	 * Lay danh sach kiem ke theo ngay
	 */
	@Query("SELECT o FROM InventoryHistory o WHERE o.store.storeID =: storeID and o.countingTime =: countingTime")
	List<InventoryHistory> getByStoreIDAndDate(int storeID, Timestamp countingTime);
}
