package com.spring.main.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.main.Service.CategoriesSevice;
import com.spring.main.model.Categories;

@RestController
@RequestMapping("/bachhoa/api/")
public class CategoriesAPI {
	@Autowired
	CategoriesSevice categoriesSevice;

	@GetMapping("categories")
	public List<Categories> getAll() {
		return categoriesSevice.findALL();
	}

	@GetMapping("{storeId}/categories")
	public List<Categories> getAllByStoreId(@PathVariable("storeId") int storeId) {
		return categoriesSevice.findALLByStoreId(storeId);
	}

	@PostMapping("categories")
	public Categories addCategory(@RequestBody Categories entity) {
		return categoriesSevice.save(entity);
	}

	@PutMapping("categories/{id}")
	public Categories updateCategory(@PathVariable("id") int id, @RequestBody Categories entity) {
		Categories category = categoriesSevice.findById(id);
		if (category != null) {
			category.setCategoriesName(entity.getCategoriesName());
			return categoriesSevice.save(category);
		}
		return null;
	}

	@DeleteMapping("categories/{id}")
	public void deleteCategory(@PathVariable("id") int id) {
		categoriesSevice.deleteById(id);
	}
}