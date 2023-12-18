package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ProductService;
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
	// Specify the directory to save the file
	@Value("${file.upload.directory}")
	private String uploadDirectory;

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

	// Start API admin
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

	// Get product image in server
	@GetMapping("/bachhoaimg/{imageName}")
	public ResponseEntity<Resource> serveImage(@PathVariable String imageName) throws IOException {
		// Get the image path from a customizable parameter
		String imagePath = getImagePath(imageName);

		// Create a FileSystemResource from the image path
		try {
			Resource resource = new FileSystemResource(imagePath);
			// Set up the response headers
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(null);
		}

	}

	// Helper method to get the image path based on the image name
	private String getImagePath(String imageName) {
		// If the image name starts with http, then it's a URL
		if (imageName.startsWith("http://")) {
			imageName = imageName.substring(imageName.lastIndexOf("/") + 1);
		}
		return uploadDirectory + imageName;
	}

	// Save new product
	@PostMapping("/bachhoa/api/products")
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	@PostMapping("/bachhoa/api/image/upload")
	public ResponseEntity<String> postMethodName(@RequestParam("file") MultipartFile file) {
		String result = productService.uploadImage(file);
		if (result.startsWith("Error")) {
			return ResponseEntity.ok("Image uploaded successfully" + result);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
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

	// End api admin

}
