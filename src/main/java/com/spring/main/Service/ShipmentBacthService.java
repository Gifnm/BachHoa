package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.ShipmentBacthJPA;
import com.spring.main.model.ShipmentBatch;

@Service
public class ShipmentBacthService {
	@Autowired
	ShipmentBacthJPA shipmentBacthJPA;
	public List<ShipmentBatch> findAll (){
		List<ShipmentBatch> list = shipmentBacthJPA.findAll();
		return list;
	}
	public boolean setFinish(String smbID) {
		return shipmentBacthJPA.setFinish(smbID);
	}
}
