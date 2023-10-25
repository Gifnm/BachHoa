package com.spring.main.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.jpa.ProductJPA;
import com.spring.main.model.Product;
@Service
public class ProductService {
@Autowired
ProductJPA productJPA;
private final String FOLDER_PATH="C:\\bachhoaimg\\";
public String uploadProduct(MultipartFile file, Product product) throws IllegalStateException, IOException {
	String filePath = FOLDER_PATH+file.getOriginalFilename();
	file.transferTo(new File(filePath));
	product.setImage("http://192.168.1.5:8083/bachhoaimg//"+file.getOriginalFilename());
	productJPA.save(product);
	return "Succes";
	
}
private void save(Product product) {
	productJPA.save(product);
	
}
public Product getByID(String productID) {
	Product product = productJPA.findById(productID).get();
	return product;
	
}
public Product getByIDAndStoreID(String ProductID, int storeID) {
	Product product = productJPA.getByIDAndStoreID(ProductID, storeID);
	return product;
	
}

public Product getByIDOrName(String value) {
	Product product = productJPA.getByIDOrName(value);
	return product;
	
}

public List<String> getProductID() {
	return productJPA.getProductID();
	
}
public List<String> getProductName() {
	return productJPA.getProductName();
	
}
}
