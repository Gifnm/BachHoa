package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
