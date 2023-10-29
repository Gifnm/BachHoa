package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Product;

public interface ProductJPA extends JpaRepository<Product, String> {
	@Query(value = "Select * from products where productID  = ?1 and storeID = ?2", nativeQuery = true)
	Product getByIDAndStoreID(String productID, int storeID);

	@Query("SELECT o.productID FROM Product o")
	List<String> getProductID();
}
