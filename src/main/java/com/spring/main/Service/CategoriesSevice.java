package com.spring.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.CategoriesJPA;
import com.spring.main.model.Categories;

@Service
public class CategoriesSevice {
	@Autowired
	CategoriesJPA categoriesJPA;

	public List<Categories> findALL() {
		List<Categories> categories = categoriesJPA.findAll();
		return categories;
	}

	public Categories findById(int id) {
		Optional<Categories> category = categoriesJPA.findById(id);
		return category.orElse(null);
	}

	public Categories save(Categories entity) {
		return categoriesJPA.save(entity);
	}

	public void deleteById(int id) {
		categoriesJPA.deleteById(id);
	}
}
