package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.model.DetailedDeliveryNote;

public interface DetailedDeliveryNoteJPA extends JpaRepository<DetailedDeliveryNote, String> {

}
