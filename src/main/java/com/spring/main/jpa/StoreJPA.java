package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.Store;

public interface StoreJPA extends JpaRepository<Store, Integer>{

}
