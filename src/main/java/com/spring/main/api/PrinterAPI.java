package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.main.Service.PrinterService;
import com.spring.main.model.Printers;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/printer/")
public class PrinterAPI {
	@Autowired
	PrinterService printerService;

	@GetMapping("getAll/{storeID}")
	private List<Printers> getAll(@PathVariable("storeID") int storeID) {
		System.out.println("getAll/{storeID} " + storeID);
		return printerService.getAllByStoreID(storeID);
	}

	@PostMapping("insert")
	private ResponseEntity<Void> insert(@RequestBody Printers printer) {
		System.out.println("insert "+ printer.getIpAddress());
		if (printerService.getByIp(printer.getIpAddress())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			printerService.insert(printer);
			return ResponseEntity.ok(null);
		}

	}

	@DeleteMapping("delete")
	private void delete(@RequestBody Printers printer) {
		printerService.delete(printer);
	}

}
