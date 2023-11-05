package com.spring.main.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.jpa.ProductJPA;
import com.spring.main.model.Product;
import com.spring.main.model.Store;

@Service
public class ProductService {
	@Autowired
	ProductJPA productJPA;
	/**
	 * Duong dan thu muc chua anh
	 */
	private final String FOLDER_PATH = "C:\\bachhoaimg\\";

	/**
	 * Tao moi, cap nhat san pham kem hinh anh Tham so bao gom Object Product va
	 * File hinh anh
	 * 
	 * @param product Object San pham
	 * @param file    File hinh Anh
	 */
	public String uploadProduct(MultipartFile file, Product product) throws IllegalStateException, IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		file.transferTo(new File(filePath));
		// Dia chi IP: Dia chi ip cuc bo may chu server
		// Cai dat ip tinh : 192.168.1.5
		product.setImage("http://192.168.1.6:8083/bachhoaimg//" + file.getOriginalFilename());
		productJPA.save(product);
		return "Succes";

	}

	/**
	 * Cap nhat hoac luu mot san pham khong kem hinh anh
	 * 
	 * @param product Object San pham
	 */
	private void save(Product product) {
		productJPA.save(product);

	}

	/**
	 * Tim kiem san pham
	 * 
	 * @param productID Ma san pham
	 * @param storeID   Ma so cua hang
	 */
	public Product getByIDAndStoreID(String productID, int storeID) {
		Product product = productJPA.getByIDAndStoreID(productID, storeID);
		return product;

	}

	/**
	 * Xoa mot san pham
	 * 
	 * @param product Object San pham
	 */
	public void delete(Product product) {
		productJPA.delete(product);

	}

	/**
	 * Chuyen doi trang thai san pham Su dung khi co so du lieu da phat sinh du lieu
	 * lien quan den san pham
	 * 
	 * @param status    Trang thai ma true hoac false
	 * @param storeID   Ma cua hang
	 * @param productID Ma san pham
	 */
	public void setStatus(boolean status, int storeID, String productID) {
		productJPA.setStatus(status, storeID, productID);
	}

	/**
	 * Lay danh sach san pham tai cua hang
	 * 
	 * @param store Object Cua hang
	 */
	public List<Product> findByStore(Store store) {
		return productJPA.findByStore(store);
	}
}
