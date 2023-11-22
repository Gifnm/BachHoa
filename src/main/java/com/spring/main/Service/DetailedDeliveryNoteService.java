package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DetailedDeliveryNoteJPA;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;

@Service
public class DetailedDeliveryNoteService {
	@Autowired
	DetailedDeliveryNoteJPA deliveryNoteJPA;

	/**
	 * Luu phieu nhap chi tiet
	 * 
	 * @param
	 */
	public void insert(DetailedDeliveryNote deliveryNote) {
		deliveryNoteJPA.save(deliveryNote);
	}

	public void update(DetailedDeliveryNote detailedDeliveryNote) {
		deliveryNoteJPA.save(detailedDeliveryNote);
	}

	/**
	 * Lay danh sach phieu nhap
	 * 
	 * @param
	 */
	public List<DetailedDeliveryNote> getall(String id) {
		List<DetailedDeliveryNote> list = deliveryNoteJPA.getAllByID(id);
		return list;
	}

	public void delete(DetailedDeliveryNote deliveryNote) {
		deliveryNoteJPA.delete(deliveryNote);
	}
}
