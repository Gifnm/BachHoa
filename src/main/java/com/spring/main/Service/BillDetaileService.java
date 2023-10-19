package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillDetailJPA;
import com.spring.main.model.BillDetail;
@Service
public class BillDetaileService {
@Autowired
BillDetailJPA billDetailJPA;
public void createBillDetail(BillDetail billDetail) {
	billDetailJPA.save(billDetail);
	
}
}
