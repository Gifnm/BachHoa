package com.spring.main.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DeliveryNoteJPA;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.Store;
@Service
public class DeliveryNoteService {
	@Autowired
	DeliveryNoteJPA deliveryNoteJPA;

	/**
	 * Lay danh sach phieu nhap hang
	 * 
	 * @param store Object cua hang
	 */
	public List<DeliveryNote> getAll(int storeID) {
		List<DeliveryNote> list = deliveryNoteJPA.getAllByStore(storeID);
		return list;
	}
	public List<DeliveryNote> getAll2() {
		List<DeliveryNote> list = deliveryNoteJPA.findAll();
		return list;
	}
	public void setFinish(String id) {
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
		Date resultdate = new Date(yourmilliseconds);
		deliveryNoteJPA.setFinish(resultdate, id);
		
	}
}
