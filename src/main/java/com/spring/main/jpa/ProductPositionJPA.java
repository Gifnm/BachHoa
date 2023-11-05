package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

public interface ProductPositionJPA extends JpaRepository<ProductPositioning, Integer> {
	@Query(value = "SELECT * FROM productpositioning WHERE disPlaID = ?1 and disSheID =?2 and storeID = ?3", nativeQuery = true)
	List<ProductPositioning> getALL(int disPlaID, int disSheID, int storeID);

	//
	@Query(value = "Select * from productpositioning where productID = ?1 and storeID = ?2", nativeQuery = true)
	ProductPositioning findByIDAndStoreID(String productID, int storeID);

	ProductPositioning findByProductAndStore(Product product, Store store);
}
