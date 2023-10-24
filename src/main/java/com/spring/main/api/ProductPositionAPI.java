package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;

import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.DisplayPlatter;
import com.spring.main.model.DisplayShelves;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;
import org.springframework.http.ResponseEntity;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class ProductPositionAPI {
	@Autowired
	ProductPosionService productPosionService;
	@Autowired
	ProductService productService;

/*
 *  Lay danh sach vi tri phan pham theo mam
 *  Tham so: ma cua hang, ma so ke, ma so mam
 * */
	@GetMapping("productPositioning/{shelfID}/{platterNb}/{storeID}")
	private List<ProductPositioning> getALL(@PathVariable("shelfID") int shelfID,
			@PathVariable("platterNb") int platterNb, @PathVariable("storeID") int storeID) {
		System.out.println("get list Position");
		List<ProductPositioning> list = productPosionService.getAllPosstion(platterNb, shelfID, storeID);
		System.out.println(list.size());
		return list;
	}

/*
 * Them vi tri san pham vao cua hang
 * Tham so truyen vao Object (productPositioning)
 * */
	@PostMapping("productPositioning/insert")
	private ResponseEntity<ProductPositioning> insert(@RequestBody ProductPositioning productPositioning) {
		ProductPositioning productPos = productPosionService.getByIDAndStoreID(
				productPositioning.getProduct().getProductID(), productPositioning.getStore().getStoreID());
		ProductPositioning productPositioning2 = productPositioning;
		System.out.println(productPositioning2.getProduct().getProductID() + "bar");
		Product product = productService.getByIDAndStoreID(productPositioning.getProduct().getProductID(), productPositioning.getStore().getStoreID());
		productPositioning2.setProduct(product);
		productPosionService.insert(productPositioning);
		return ResponseEntity.ok(productPositioning2);
	}

	/*
	 * Xem vi tri san pham
	 * Tra ve Vi tri cua san pham tai cua hang (ProductPositioning)
	 * Tham so: Ma san pham, ma cua hang
	 * */
	@GetMapping("productPosition/findByID/{id}/{storeID}")
	private ResponseEntity<ProductPositioning> getByIdAndStoreID(@PathVariable("id") String productID,
			@PathVariable("storeID") int storeID) {
		ProductPositioning product = productPosionService.getByIDAndStoreID(productID, storeID);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} else {
			return ResponseEntity.ok(product);

		}
	}

/*
 * Kiem tra san pham da co vi tri chua, va san pham co ton tai khong
 * */
	@GetMapping("productPosition/checkProduct/{id}/{storeID}")
	private ResponseEntity<Boolean> checkProductID(@PathVariable("id") String productID,
			@PathVariable("storeID") int storeID) {
		// Kiem tra san pham co ton tai khong
		Product productEs= productService.getByIDAndStoreID(productID,storeID);
		if(productEs == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		}
		else {
		// Kiem tra san pham co vi tri khong
		ProductPositioning product = productPosionService.getByIDAndStoreID(productID, storeID);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

		} else {
			return ResponseEntity.ok(true);
		}}
	}

	// Xoa vi tri san pham
	// Tham so: ma san pham va ma cua hang
	@DeleteMapping("productPosition/delete")
	private void deleteProductPosition(@RequestPart("productPos") ProductPositioning productPos) {
		productPosionService.deleteByProductID_StoreID(productPos);
	}
}
