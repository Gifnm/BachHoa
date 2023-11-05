package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ProductPositionJPA;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

@Service
public class ProductPosionService {
	@Autowired
	ProductPositionJPA productPositionJPA;

	/**
	 * Lay danh sach vi tri san pham theo mam trung bay
	 * 
	 * @param platterID Ma mam trung bay
	 * @param shelfID   Ma ke trung bay
	 * @param storeID   Ma cua hang
	 */
	public List<ProductPositioning> getAllPosstion(int platterID, int shelfID, int storeID) {
		List<ProductPositioning> list = productPositionJPA.getALL(platterID, shelfID, storeID);
		return list;

	}

	/**
	 * Luu & cap nhat vi tri moi cua san pham
	 * 
	 * @param productPositioning Object vi tri san pham
	 */
	public void insert(ProductPositioning productPositioning) {
		productPositionJPA.save(productPositioning);

	}

	/**
	 * Lay vi tri san pham cu the tai cua hang
	 * 
	 * @param productID san pham
	 * @param storeID   Ma cua hang
	 */
	public ProductPositioning getByIDAndStoreID(String productID, int storeID) {
		ProductPositioning proPositioning = productPositionJPA.findByProductAndStore(new Product(productID),
				new Store(storeID));
		return proPositioning;
	}

	/**
	 * Xoa vi tri san pham
	 * 
	 * @param productPositioning Object vi tri san pham
	 */
	public void deleteByProductID_StoreID(ProductPositioning productPositioning) {
		productPositionJPA.delete(productPositioning);

	}

	/**
	 * Lay danh vi tri theo cua hang va ke
	 * 
	 * @param storeID  Ma cua hang
	 * @param disSheID Ma ke
	 */
	public List<ProductPositioning> getPosByShlef(int storeID, int disSheID) {
		List<ProductPositioning> list = productPositionJPA.getByShelfandStore(storeID, disSheID);
		return list;
	}
}
