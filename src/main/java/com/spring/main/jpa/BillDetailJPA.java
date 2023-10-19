package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.BillDetail;

public interface BillDetailJPA extends JpaRepository<BillDetail, String>{

}
