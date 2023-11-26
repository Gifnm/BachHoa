package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.DisplayShelves;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;

//import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductAPI {
	@Autowired
	ProductService productService;
	@Autowired
	ProductPosionService posionService;

	// Su dung khi tao san pham moi
	@PostMapping("/bachhoa/api/upload")
	public ResponseEntity<String> uploadSanPhamWithImage(@RequestPart("product") Product product,
			@RequestPart("image") MultipartFile hinhAnh) throws IOException {
		System.out.println("Hello Post");
		try {

			productService.uploadProduct(hinhAnh, product);
			ProductPositioning productPositioning = new ProductPositioning();
			productPositioning.setStore(product.getStore());
			productPositioning.setProduct((Product) product);
			productPositioning.setId(0);
			posionService.insert(productPositioning);
			return ResponseEntity.ok("Ok");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
		}
	}

	@GetMapping("/bachhoa/api/getproduct")
	public ResponseEntity<?> getProduct() throws IOException {
		byte[] img = Files.readAllBytes(
				new File("C:\\bachhoaimg\\Screenshot_2023-09-19-12-22-31-463_com.google.android.apps.maps.jpg")
						.toPath());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(img);

	}

	@GetMapping("/sanpham")
	public String getSanPhamHinhAnh() {

		return "Screenshot_2023-09-19-12-22-31-463_com.google.android.apps.maps.jpg";
	}

	@GetMapping("product/findByID/{id}/{storeID}")
	private Product getByIdAndStoreID(@PathVariable("id") String productID, @PathVariable("storeID") int storeID) {
		Product product = productService.getByIDAndStoreID(productID, storeID);
		if (product == null) {
			return null;
		} else {
			return product;

		}
	}

	@GetMapping("product/getProductID")
	public List<String> getProductID() {
		return productService.getProductID();
	}

	@GetMapping("product/findByID/{productID}")
	public Product findByID(@PathVariable("productID") String productID) {
		return productService.getByID(productID);
	}

	@GetMapping("product/getProductName")
	public List<String> getProductName(@RequestParam("storeID") Integer storeID) {
		return productService.getProductName(storeID);
	}

	@GetMapping("product/findByIDOrName/{value}")
	public Product findByIDOrName(@PathVariable("value") String value) {
		return productService.getByIDOrName(value);
	}

	// Start API thanhdq
	// Return all product in database
	@GetMapping("/bachhoa/api/products")
	public List<Product> getAll() {
		return productService.getAll();
	}

	// Return all product which have specific store-id
	@GetMapping("/bachhoa/api/products/{store-id}")
	public List<Product> getAllByStoreId(@PathVariable("store-id") int storeId) {
		return productService.getAllByStoreId(storeId);
	}

	// Return all product which map with keyword
	@GetMapping("/bachhoa/api/products/search")
	public List<Product> searchByKeyword(@RequestParam(name = "q") String keyWord,
			@RequestParam(name = "storeid") int storeId) {
		System.out.println("[ProductAPI:searchByKeyword():84]\n" + //
				"> calling service...");
		return productService.getByKeyword(keyWord, storeId);
	}

	// Save new product
	@PostMapping("/bachhoa/api/products")
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	// Update product
	@PutMapping("/bachhoa/api/products/{id}")
	public Product update(@PathVariable("id") String id, @RequestBody Product product) {
		return productService.update(product);
	}

	@PutMapping("/bachhoa/api/products/update")
	public Product update(@RequestBody Product product) {
		return productService.update(product);
	}

	// Delete product
	@DeleteMapping("/bachhoa/api/products/{id}")
	public void delete(@PathVariable("id") String id) {
		productService.delete(id);
	}

	// End api thanhdq

}
