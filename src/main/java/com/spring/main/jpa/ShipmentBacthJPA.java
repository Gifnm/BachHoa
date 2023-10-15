package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.ShipmentBatch;

public interface ShipmentBacthJPA  extends JpaRepository<ShipmentBatch, String>{

}
