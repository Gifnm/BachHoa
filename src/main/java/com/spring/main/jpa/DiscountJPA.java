package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.Discount;

public interface DiscountJPA extends JpaRepository<Discount, String> {

}
