package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.ProductService;
import com.spring.main.model.Employee;
import com.spring.main.model.Product;
import com.spring.main.model.Store;

import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductAPI {
@Autowired
ProductService productService;
/*Muc luc
 * 1. Them san pham (co hinh anh) - Cap nhat thong tin Product (Co hinh anh)
 * 2. Lay san pham bang ma san pham
 * 3. Xoa san pham
 * 4. Lay danh sach san pham
 * 5. Cap nhat thong tin Product - khong thay doi hinh anh
 * */

/* 1. Them san pham - co hinh anh
 * 
 * */
    @PostMapping("/bachhoa/api/upload")
    public ResponseEntity<String> uploadSanPhamWithImage(
    		
            @RequestPart("product") Product product,
            @RequestPart("image") MultipartFile hinhAnh
    ) throws IOException {
        System.out.println("Hello Post");
        try {
        	
        productService.uploadProduct(hinhAnh, product);
       
           
            return ResponseEntity.ok("Ok");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
        }
    }
    
//    @GetMapping("/bachhoa/api/getproduct")
//	public ResponseEntity<?> getProduct() throws IOException{
//    	byte[] img = Files.readAllBytes(new File("C:\\bachhoaimg\\Screenshot_2023-09-19-12-22-31-463_com.google.android.apps.maps.jpg").toPath());
//    	return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(img);
//    	
//    }
    /* 2. Lay san pham bang ma san pham
     * 
     * */
    @GetMapping("product/findByID/{id}/{storeID}")
    private Product getByIdAndStoreID(@PathVariable("id") String productID, @PathVariable("storeID") int storeID) {
    	Product product = productService.getByIDAndStoreID(productID, storeID);
    	if(product == null) {
    		return null;
    	}
    	else {
    		return product;
    		
    	}
    }
    
    /* 3. Xoa san pham
     *  - Tham so: Product
     * */
    @DeleteMapping("product/delete")
    private void delete(@RequestPart("product") Product product) {
    	productService.delete(product);
    }
    
    /*4. Lay danh sach san pham
     * - Tham so: Ma kho (storeID)
     * */
    @GetMapping("product/findByStoreID/{storeID}")
    private ResponseEntity<List<Product>> findAll( @PathVariable("storeID") int storeID){
    	List<Product> list = productService.findByStore(new Store(storeID));
    	if(list.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    		
    	}
    	else {
    		return ResponseEntity.ok(list);
    		
    	}
    	
    }
}
		
	

