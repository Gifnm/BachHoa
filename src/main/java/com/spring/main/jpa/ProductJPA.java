package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.Product;
import com.spring.main.model.Store;

public interface ProductJPA extends JpaRepository<Product, String> {
	/*
	 * 1. Lay thong tin san pham - Tham so: Ma san pham (productID), storeID
	 */
	@Query(value = "Select * from products where productID  = ?1 and storeID = ?2", nativeQuery = true)
	Product getByIDAndStoreID(String productID, int storeID);

	/*
	 * 2. Chuyen trang thai kinh doanh: - Tham so: productID, storeID
	 */
	@Query("UPDATE Product p SET p.status = :status WHERE p.store.storeID = :storeID AND p.productID = :productID")
	void setStatus(@Param("status") boolean status, @Param("storeID") int storeID, @Param("productID") String productID);

	
	/* 3. 
	 * */
	List<Product> findByStore(Store store);
}
