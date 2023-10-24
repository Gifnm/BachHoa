package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.DisplayPlatter;
import com.spring.main.model.DisplayShelves;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

public interface ProductPositionJPA extends JpaRepository<ProductPositioning, Integer>{
	/* 1
	 * Lay danh sach vi tri theo mam
	 * Tham so: ma so cua hang, ma so ke, ma so mam
	 * Dung khi: 
	 * + Can hien thi danh sach vi tri san pham theo mam trung bay
	 * + in tem gia theo mam
	 * */
	@Query(value = "SELECT * FROM product_positionings WHERE disPlaID = ?1 and disSheID =?2 and storeID = ?3", nativeQuery = true)
	List<ProductPositioning> getALL(int disPlaID, int disSheID, int storeID );
	/* 2
	 * Lay danh sach vi tri theo theo ke
	 * Tham so: ma so cua hang, ma so ke, ma so mam
	 * Dung khi: 
	 * + in tem gia theo ke trung bay
	 * */
	@Query(value =  "Select * from product_positionings where productID = ?1 and storeID = ?2", nativeQuery = true)
	ProductPositioning findByIDAndStoreID(String productID, int storeID);
	//
	 ProductPositioning findByProductAndStore(Product product, Store store);


	 
}
