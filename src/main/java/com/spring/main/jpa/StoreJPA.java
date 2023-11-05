package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.model.Store;

public interface StoreJPA extends JpaRepository<Store, Integer>{
	@Query("SELECT o FROM Store o WHERE o.address LIKE ?1")
	Store findByAddress(String address);
	
}
