package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DisplayPlatterService;
import com.spring.main.model.DisplayPlatter;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class DisplayPlatterAPI {

	@Autowired 
	DisplayPlatterService displayPlatterService;
	
	// 
	@GetMapping("platter/getPlatters/{id}")
	private List<DisplayPlatter> getPlatters(@PathVariable int id){
		System.out.println("get Platter");
		return displayPlatterService.getAllPlatter(id);
	}
	
	@PostMapping("platter/inserPlatter")
	private void insert(@RequestBody DisplayPlatter displayPlatter) {
		System.out.println("add platter");
		displayPlatterService.insert(displayPlatter);
		
	}
}
