package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Categories;
import java.util.List;

public interface CategoriesJPA extends JpaRepository<Categories, Integer> {
    @Query(value = "Select * from categories where storeID = ?1", nativeQuery = true)
    public List<Categories> findByStoreId(int storeId);
}
