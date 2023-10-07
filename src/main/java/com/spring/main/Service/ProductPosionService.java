package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ProductPositionJPA;
import com.spring.main.model.ProductPositioning;

@Service
public class ProductPosionService {
	@Autowired
	ProductPositionJPA productPositionJPA;

	public List<ProductPositioning> getAllPosstion(int platterID, int shelfID, int storeID) {
		List<ProductPositioning> list = productPositionJPA.getALL(platterID, shelfID, storeID);
		return list;

	}

	public void insert(ProductPositioning productPositioning) {
		productPositionJPA.save(productPositioning);

	}

	public ProductPositioning getByIDAndStoreID(String productID, int storeID) {
		ProductPositioning proPositioning = productPositionJPA.findByIDAndStoreID(productID, storeID);
		return proPositioning;
	}
}
