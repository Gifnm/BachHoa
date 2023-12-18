package com.spring.main.jpa;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.DiscountDetails;

public interface DiscountDetailJPA extends JpaRepository<DiscountDetails, String> {
	@Query("SELECT o FROM DiscountDetails o WHERE (o.productID LIKE ?1 AND o.storeID LIKE ?2) AND o.activity = 1")
	DiscountDetails findDiscountIsActive(String productID, Integer storeID);

	@Query("SELECT o FROM DiscountDetails o WHERE o.storeID LIKE ?1")
	Page<DiscountDetails> findByStoreID(Integer storeID, Pageable page);
	
	@Query("SELECT o FROM DiscountDetails o WHERE o.storeID LIKE ?1 AND o.startTime <= ?2 AND o.endTime >= ?3")
	Page<DiscountDetails> findByDate(Integer storeID, Date startTime, Date endTime, Pageable page);
	
	@Query("SELECT o FROM DiscountDetails o WHERE o.storeID LIKE ?1 AND o.productID LIKE ?2")
	List<DiscountDetails> findByStoreIDAndProductID(Integer storeID, String productID);

	@Transactional
	@Modifying
	@Query(value = "UPDATE discount_details SET activity = 1, startTime = ?1, endTime = ?2 WHERE productID = ?3 AND storeID = ?4", nativeQuery = true)
	void update(Date startTime, Date endTime, String productID, Integer storeID);
	@Query("SELECT o FROM DiscountDetails o WHERE o.productID LIKE ?1 AND o.storeID LIKE ?2")
	DiscountDetails findByProductIDAndStoreID(String productID, Integer storeID);

}
