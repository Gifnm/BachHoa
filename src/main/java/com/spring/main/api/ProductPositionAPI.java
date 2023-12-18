package com.spring.main.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.EntityDTO.PriceTag;
import com.spring.main.Service.DiscountDetailService;
import com.spring.main.Service.ProductPosionService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.Product;
import com.spring.main.model.ProductPositioning;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/productPosition/")
public class ProductPositionAPI {
	@Autowired
	ProductPosionService productPosionService;
	@Autowired
	ProductService productService;
	@Autowired
	DiscountDetailService discountDetailService;

	// Tra ve danh sach vi tri san pham theo ke - mam - cua hang
	@GetMapping("{shelfID}/{platterNb}/{storeID}")
	private List<ProductPositioning> getALL(@PathVariable("shelfID") int shelfID,
			@PathVariable("platterNb") int platterNb, @PathVariable("storeID") int storeID) {
		System.out.println(shelfID +" - "+ platterNb +" - "+ storeID);
		System.out.println("m");
		List<ProductPositioning> list = productPosionService.getAllPosstion(platterNb, shelfID, storeID);
		return list;
	}

	// Them mot vi tri san pham moi
	@PostMapping("insert")
	private ProductPositioning insert(@RequestBody ProductPositioning productPositioning) {
		ProductPositioning productPositioning2 = productPositioning;
		System.out.println(productPositioning2.getProduct().getProductID() + "bar");
		ProductPositioning productPositioningOld = productPosionService.getByIDAndStoreID(productPositioning.getProduct().getProductID(),productPositioning.getStore().getStoreID());
		if(productPositioningOld!= null) {
			productPosionService.delete(productPositioningOld);
			
		}
		Product product = productService.getByID(productPositioning.getProduct().getProductID());
		productPositioning2.setProduct(product);
		productPosionService.insert(productPositioning);
		return productPositioning2;
	}

	// Tra ve vi tri san pham tai cua hang
	@GetMapping("findByID/{id}/{storeID}")
	private ProductPositioning getByIdAndStoreID(@PathVariable("id") String productID,
			@PathVariable("storeID") int storeID) {
		ProductPositioning productPositioning = productPosionService.getByIDAndStoreID(productID, storeID);
		if (productPositioning == null) {
			return null;
		} else {
			System.out.println("10d");
			return productPositioning;

		}
	}

	@GetMapping("getPriceTag/{storeID}/{productID}")
	private ResponseEntity<PriceTag> getPriceTag(@PathVariable("storeID") int storeID,
			@PathVariable("productID") String productID) {
		ProductPositioning productPositioning = productPosionService.getByIDAndStoreID(productID, storeID);
		if (productPositioning == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			PriceTag priceTag = new PriceTag();
			priceTag.setProductPositioning(productPositioning);
			// priceTag.setDiscountDetails(discountDetailService.findByProductIDAndStoreID(productID, storeID));
			return ResponseEntity.status(HttpStatus.OK).body(priceTag);
		}

	}

	// dang hoang thien
	@GetMapping("getPriceTags/{storeID}/{disSheID}")
	private ResponseEntity<PriceTag> getPriceTags(@PathVariable("storeID") int storeID,
			@PathVariable("productID") String productID) {
		ProductPositioning productPositioning = productPosionService.getByIDAndStoreID(productID, storeID);
		if (productPositioning == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			PriceTag priceTag = new PriceTag();
			priceTag.setProductPositioning(productPositioning);
			// priceTag.setDiscountDetails(discountDetailService.findByProductIDAndStoreID(productID, storeID));
			return ResponseEntity.status(HttpStatus.OK).body(priceTag);
		}

	}

	@GetMapping("getPosByStoreAndShelf/{storeID}/{disSheID}")
	private ResponseEntity<List<PriceTag>> getByShelfAndStore(@PathVariable("storeID") int storeID,
			@PathVariable("disSheID") int disSheID) {
		System.out.println("getPosByStoreAndShelf/{storeID}/{disSheID}");
		List<ProductPositioning> list = productPosionService.getByShelfAndStore(storeID, disSheID);
		if (list != null) {
			List<PriceTag> liTags = new ArrayList<>();
			for (ProductPositioning productPositioning : list) {
				PriceTag priceTag = new PriceTag();
				priceTag.setProductPositioning(productPositioning);
				priceTag.setDiscountDetails(discountDetailService
						.findByProductIDAndStoreID(productPositioning.getProduct().getProductID(), storeID));
				liTags.add(priceTag);
			}
			return ResponseEntity.status(HttpStatus.OK).body(liTags);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@GetMapping("getPricetagByPlatter/{shelfID}/{platterNb}/{storeID}")
	private ResponseEntity<List<PriceTag>> getPriceTagByTagByPlatter(@PathVariable("shelfID") int shelfID,
			@PathVariable("platterNb") int platterNb, @PathVariable("storeID") int storeID) {
		System.out.println(shelfID +" - "+ platterNb +" - "+ storeID);
		List<ProductPositioning> list = productPosionService.getAllPosstion(platterNb, shelfID, storeID);
		if (list != null) {
			List<PriceTag> liTags = new ArrayList<>();
			for (ProductPositioning productPositioning : list) {
				PriceTag priceTag = new PriceTag();
				priceTag.setProductPositioning(productPositioning);
				priceTag.setDiscountDetails(discountDetailService
						.findByProductIDAndStoreID(productPositioning.getProduct().getProductID(), storeID));
				liTags.add(priceTag);
			}
			System.out.println(liTags.size() +" - 0");
			return ResponseEntity.status(HttpStatus.OK).body(liTags);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
}
