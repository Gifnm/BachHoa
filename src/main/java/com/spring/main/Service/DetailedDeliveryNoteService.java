package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DetailedDeliveryNoteJPA;
@Service
public class DetailedDeliveryNoteService {
@Autowired
DetailedDeliveryNoteJPA deliveryNoteJPA;
}
