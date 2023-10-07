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

import com.spring.main.Service.DisplayShelvesService;
import com.spring.main.model.DisplayPlatter;
import com.spring.main.model.DisplayShelves;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/")
public class DisplayShelvesAPI {
	@Autowired
	DisplayShelvesService diService;

	@GetMapping("findAllShelf/{id}")
	public List<DisplayShelves> getAllShelfs(@PathVariable int id) {

		List<DisplayShelves> list = diService.findAll(id);
		System.out.println(list.size());
		return list;
	}

	@PostMapping("insertShelf")
	private void insert(@RequestBody DisplayShelves diShelves) {
		System.out.println("ADD platter");
		diService.insert(diShelves);
	}
}
