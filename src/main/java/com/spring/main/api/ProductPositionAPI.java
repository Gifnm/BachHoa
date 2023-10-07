package com.spring.main.api;

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

	@GetMapping("productPositioning/{shelfID}/{platterNb}/{storeID}")
	private void getALL(@PathVariable("shelfID") int shelfID, @PathVariable("platterNb") int platterNb,
			@PathVariable("storeID") int storeID) {
		productPosionService.getAllPosstion(platterNb, shelfID, storeID);
	}

	@PostMapping("productPositioning/insert")
	private ProductPositioning insert(@RequestBody ProductPositioning productPositioning) {
		System.out.println("Halo Post Position");
		ProductPositioning productPositioning2 = productPositioning;
		
		Product product = productService.getByID(productPositioning.getProduct().getProductID());
		productPositioning2.setProduct(product);
		System.out.println(productPositioning.getDisplayPlatter().getDisPlaID()+" -9");
		productPositioning2.setDisplayPlatter(new DisplayPlatter(1));
		productPosionService.insert(productPositioning2);
		// productPosionService.insert(productPositioning);
		return productPositioning2;
	}
	@GetMapping("productPosition/findByID/{id}/{storeID}")
    private ProductPositioning getByIdAndStoreID(@PathVariable("id") String productID, @PathVariable("storeID") int storeID) {
    	ProductPositioning product = productPosionService.getByIDAndStoreID(productID, storeID);
    	if(product == null) {
    		return null;
    	}
    	else {
    		return product;
    		
    	}
    }
}
