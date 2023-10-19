package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthDetailJPA;
import com.spring.main.model.ShipmentBatchDetail;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthDetailService {
@Autowired
ShipmentBacthDetailJPA shipmentBacthDetailJPA;
public List<ShipmentBatchDetail> getALl(Store storeID) {
	//List<ShipmentBatchDetail> list = shipmentBacthDetailJPA.findByStoreID(storeID);
	return null;
}
public Boolean deleteOne() {
	
	return true;
}
}
