package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.DeliveryNote;
import com.spring.main.model.Store;

public interface DeliveryNoteJPA extends JpaRepository<DeliveryNote, String>{
List<DeliveryNote> getByStore(Store store);
}
