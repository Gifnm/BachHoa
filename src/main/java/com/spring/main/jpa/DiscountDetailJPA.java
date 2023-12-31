package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.DiscountDetails;

public interface DiscountDetailJPA extends JpaRepository<DiscountDetails, String> {
	@Query("SELECT o FROM DiscountDetails o WHERE o.productID LIKE ?1 AND o.storeID LIKE ?2")
	DiscountDetails findByProductIDAndStoreID(String productID, Integer storeID);
}
