package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.DeliveryNote;

public interface DeliveryNoteJPA extends JpaRepository<DeliveryNote, String>{

}
