package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillJPA;
import com.spring.main.model.Bill;

@Service
public class BillService {
@Autowired
BillJPA billJPA;
public void save(Bill bill) {
	billJPA.save(bill);	
}

}
