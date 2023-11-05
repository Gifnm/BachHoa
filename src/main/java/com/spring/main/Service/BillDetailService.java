package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.main.jpa.BillDetailJPA;
import com.spring.main.model.BillDetail;

@Service
public class BillDetailService {
	@Autowired
	BillDetailJPA billDetailJPA;

	public BillDetail save(BillDetail billDetail) {
		billDetailJPA.save(billDetail);
		return billDetail;

	}

	public void delete(String billID, String productID) {
		billDetailJPA.deleteByBillIDAndProductID(billID, productID);
	}

	public List<BillDetail> findByBillID(String billID) {
		return billDetailJPA.findByBillID(billID);
	}

}
