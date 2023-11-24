package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.ProductIsOut;
import com.spring.main.model.Store;

public interface ProductIsOutJPA extends JpaRepository<ProductIsOut, String>{
List<ProductIsOut> getByStore(Store store);
}
