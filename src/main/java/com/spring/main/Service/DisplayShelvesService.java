package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DisplayShelvesJPA;
import com.spring.main.model.DisplayShelves;

@Service
public class DisplayShelvesService {
@Autowired
DisplayShelvesJPA diShelvesJPA;

public List<DisplayShelves> findAll(int id ){
	List<DisplayShelves> list = diShelvesJPA.getByStoreID(id);
	return list;
}
public void insert(DisplayShelves displayShelves) {
	diShelvesJPA.save(displayShelves);
	
}
}
