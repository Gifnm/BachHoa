package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.main.jpa.DeliveryNoteJPA;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.Store;

public class DeliveryNoteService {
	@Autowired
	DeliveryNoteJPA deliveryNoteJPA;

	/**
	 * Lay danh sach phieu nhap hang
	 * 
	 * @param store Object cua hang
	 */
	public List<DeliveryNote> getALl(Store store) {
		List<DeliveryNote> list = deliveryNoteJPA.getByStore(store);
		return list;
	}
}
