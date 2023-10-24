package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.DisplayPlatterJPA;
import com.spring.main.model.DisplayPlatter;

@Service
public class DisplayPlatterService {
@Autowired
DisplayPlatterJPA displayPlatterJPA;

public List<DisplayPlatter> getAllPlatter(int id){
	return displayPlatterJPA.getByStoID(id);
	
}
public void insert(DisplayPlatter diPlatterService) {	
	displayPlatterJPA.save(diPlatterService);
}
}
