package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.DetailedDeliveryNoteID;

public interface DetailedDeliveryNoteJPA extends JpaRepository<DetailedDeliveryNote, DetailedDeliveryNoteID>{
//	List<DetailedDeliveryNote> getByDeliverNoteID(String deliveryNoteId);

}
