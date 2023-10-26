package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.ProductService;
import com.spring.main.model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductAPI {
    @Autowired
    ProductService productService;

    @PostMapping("/bachhoa/api/upload")
    public ResponseEntity<String> uploadSanPhamWithImage(

            @RequestPart("product") Product product,
            @RequestPart("image") MultipartFile hinhAnh) throws IOException {
        System.out.println("Hello Post");
        try {

            productService.uploadProduct(hinhAnh, product);

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

    // Start API thanhdq
    @GetMapping("/bachhoa/api/products")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/bachhoa/api/products/search")
    public List<Product> searchByKeyword(@RequestParam(name = "q") String keyWord) {
        System.out.println("calling service...");
        return productService.getByKeyword(keyWord);
    }

    @PostMapping("/bachhoa/api/products")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/bachhoa/api/products/{id}")
    public Product update(@PathVariable("id") String id, @RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/bachhoa/api/products/{id}")
    public void delete(@PathVariable("id") String id) {
        productService.delete(id);
    }

    // End api thanhdq

}
