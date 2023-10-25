package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillDetailJPA;
import com.spring.main.model.BillDetail;

@Service
public class BillDetaileService {
	@Autowired
	BillDetailJPA billDetailJPA;

	public BillDetail save(BillDetail billDetail) {
//		ObjectMapper mapper = new ObjectMapper();
//		BillDetail billDetail = mapper.convertValue(data,  BillDetail.class);
		billDetailJPA.save(billDetail);
		return billDetail;

	}
	
	public void delete(String billDetailID) {
		billDetailJPA.deleteById(billDetailID);
	}
	
	public List<BillDetail> findByBillID(String billID) {
		return billDetailJPA.findByBillID(billID);
	}

}
