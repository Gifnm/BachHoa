package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.ProductIsOutService;
import com.spring.main.model.ProductIsOut;
import com.spring.main.model.Store;

@CrossOrigin("*")
@RestController
@RequestMapping("bachhoa/api/productisout/")
public class ProductIsOutAPI {
	@Autowired
	ProductIsOutService productIsOutService;

	@PostMapping("insert")
	private ResponseEntity<Void> insert(@RequestPart("imageDate") MultipartFile imageDate,
			@RequestPart("imageQuantity") MultipartFile imageQuantity,
			@RequestPart("productIsOut") ProductIsOut productIsOut) {

		if (productIsOutService.insert(imageDate, imageQuantity, productIsOut)) {

			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@DeleteMapping("delete")
	private void delete(@RequestBody ProductIsOut productIsOut) {
		productIsOutService.delete(productIsOut);
	}

	@GetMapping("getAll")
	private ResponseEntity<List<ProductIsOut>> getByStore(@RequestBody Store store) {
		List<ProductIsOut> list = productIsOutService.getByStore(store);
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
}
