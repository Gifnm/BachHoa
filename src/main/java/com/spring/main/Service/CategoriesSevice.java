package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.CategoriesJPA;
import com.spring.main.model.Categories;

@Service
public class CategoriesSevice {
@Autowired
CategoriesJPA categoriesJPA;
public List<Categories> findALL(){
	List<Categories> categories = categoriesJPA.findAll();
	return categories;
	
}
}
