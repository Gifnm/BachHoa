package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DeliveryNoteService;
@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/deliverynotapi/")
public class DeliveryNoteAPI {
 @Autowired 
 DeliveryNoteService deliveryNoteService;
 
 
}
