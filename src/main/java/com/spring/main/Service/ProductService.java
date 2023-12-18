package com.spring.main.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.jpa.ProductJPA;
import com.spring.main.model.Product;

@Service
public class ProductService {
	@Autowired
	ProductJPA productJPA;

	// Specify the directory to save the file
	@Value("${file.upload.directory}")
	private String uploadDirectory;

	private final String FOLDER_PATH = "C:\\bachhoaimg\\";

	public String uploadProduct(MultipartFile file, Product product) throws IllegalStateException, IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		file.transferTo(new File(filePath));
		product.setImage("http://192.168.1.5:8083/bachhoaimg//" + file.getOriginalFilename());
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

	public List<String> getProductName(Integer storeID) {
		return productJPA.getProductName(storeID);

	}

	// Start service admin
	// Get all product in database
	public List<Product> getAll() {
		return productJPA.findAll();
	}

	// Get all product in a specific store
	public List<Product> getAllByStoreId(int storeId) {
		return productJPA.getByStoreId(storeId);
	}

	// Get all product which map with keyword
	public List<Product> getByKeyword(String keyword, int storeId) {
		System.out.println("[ProductService:getByKeyWord():59]\n> calling repo with keyword '" + keyword + "'...");
		try {
			Integer.parseInt(keyword);
			System.out.println("[ProductService:getByKeyWord():62]\n> keyword after parse int: " + keyword);
			return productJPA.findByKeyword(keyword, storeId);
		} catch (Exception e) {
			keyword = "%" + keyword + "%";
			System.out.println("[ProductService:getByKeyWord():66]\n> keyword after makeup: " + keyword);
			return productJPA.findByKeyword(keyword, storeId);
		}
	}

	// Save new product
	public Product create(Product product) {
		return productJPA.save(product);
	}

	public String uploadImage(MultipartFile file) {
		// Create the directory if it doesn't exist
		File dir = new File(uploadDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// Save the file to the specified directory
		// Generate a unique file name to avoid conflicts
		// String uniqueFileName = System.currentTimeMillis() + "_" +
		// file.getOriginalFilename();
		String uniqueFileName = file.getOriginalFilename();
		// Save the file to the server
		Path filePath = Paths.get(uploadDirectory, uniqueFileName);
		System.out.println(filePath);
		try {
			file.transferTo(filePath);
			return filePath.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error uploading the file";
		}
	}

	// Update product
	public Product update(Product product) {
		return productJPA.save(product);
	}

	// Delete product
	public void delete(String id) {
		productJPA.deleteById(id);
	}
	// End service admin

}
