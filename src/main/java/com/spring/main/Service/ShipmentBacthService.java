package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthJPA;
import com.spring.main.model.ShipmentBatch;
import com.spring.main.model.Store;

@Service
public class ShipmentBacthService {
	@Autowired
	ShipmentBacthJPA shipmentBacthJPA;
	public List<ShipmentBatch> findBýtoreIDl (Store store){
		List<ShipmentBatch> list = shipmentBacthJPA.findByStore(store);
		return list;
	}
	public void insert(ShipmentBatch shipmentBatch) {
		shipmentBacthJPA.save(shipmentBatch);
	}
	public boolean setFinish(String smbID) {
		return shipmentBacthJPA.setFinish(smbID);
	}
}
