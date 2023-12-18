package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

public interface ProductPositionJPA extends JpaRepository<ProductPositioning, Integer> {
	@Query(value = "SELECT * FROM product_positionings WHERE disPlaID = ?1 and disSheID =?2 and storeID = ?3", nativeQuery = true)
	List<ProductPositioning> getALL(int disPlaID, int disSheID, int storeID);

	@Query(value = "Select * from product_positionings where productID = ?1 and storeID = ?2", nativeQuery = true)
	ProductPositioning findByIDAndStoreID(String productID, int storeID);
	 @Query ("Delete from ProductPositioning o where o.product.productID =:productID and o.store.storeID =:storeID")
	 void deletePosition(@Param("productID") String productID, @Param("storeID") int storeID);
	 
	@Query("SELECT o FROM ProductPositioning o WHERE o.store.storeID =:storeID and o.displayShelves.disSheID =:disSheID")
	List<ProductPositioning> getByShelfAndStore(@Param("storeID")int storeID, @Param("disSheID") int disSheID);

}
