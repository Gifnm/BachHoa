package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.DisplayPlatter;
import com.spring.main.model.DisplayShelves;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class ProductPositionAPI {
	@Autowired
	ProductPosionService productPosionService;
	@Autowired
	ProductService productService;

// Tra ve danh sach vi tri san pham theo ke - mam - cua hang
	@GetMapping("productPositioning/{shelfID}/{platterNb}/{storeID}")
	private List<ProductPositioning> getALL(@PathVariable("shelfID") int shelfID, @PathVariable("platterNb") int platterNb,
			@PathVariable("storeID") int storeID) {
		System.out.println("get list Position");
		List<ProductPositioning> list = productPosionService.getAllPosstion(platterNb, shelfID, storeID);
		System.out.println(list.size());
		return list;
	}

// Them mot vi tri san pham moi
	@PostMapping("productPositioning/insert")
	private ProductPositioning insert(@RequestBody ProductPositioning productPositioning) {
		System.out.println("Halo Post Position");
		ProductPositioning productPositioning2 = productPositioning;
		System.out.println(productPositioning2.getProduct().getProductID() + "bar");
		Product product = productService.getByID(productPositioning.getProduct().getProductID());
		productPositioning2.setProduct(product);
//		System.out.println(productPositioning.getDisplayPlatter().getDisPlaID()+" -9");
//		productPositioning2.setDisplayPlatter(new DisplayPlatter(1));
		productPosionService.insert(productPositioning);
		// productPosionService.insert(productPositioning);
		return productPositioning2;
	}

	// Tra ve vi tri san pham tai cua hang
	@GetMapping("productPosition/findByID/{id}/{storeID}")
	private ProductPositioning getByIdAndStoreID(@PathVariable("id") String productID,
			@PathVariable("storeID") int storeID) {
		System.out.println("Here");
		ProductPositioning product = productPosionService.getByIDAndStoreID(productID, storeID);
		if (product == null) {
			return null;
		} else {
			return product;

		}
	}
}
