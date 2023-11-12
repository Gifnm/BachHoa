package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.TextFormat.Printer;
import com.spring.main.Service.PrinterService;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/printer/")
public class PrinterAPI {
	@Autowired
	PrinterService printerService;

	@GetMapping("insert")
	private void insert(@RequestPart("printer") Printer printer) {
		printerService.insert(printer);
	}

	@DeleteMapping("delete")
	private void delete(@RequestPart("printer") Printer printer) {
		printerService.delete(printer);
	}

}
