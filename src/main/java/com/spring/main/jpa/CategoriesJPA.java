package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.Categories;

public interface CategoriesJPA extends JpaRepository<Categories, Integer>{

}
