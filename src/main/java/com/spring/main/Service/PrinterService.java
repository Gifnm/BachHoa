package com.spring.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.TextFormat.Printer;
import com.spring.main.jpa.PrinterJPA;

@Service
public class PrinterService {
	@Autowired
	PrinterJPA printerJPA;

	public void insert(Printer printer) {
		printerJPA.save(printer);
	}

	public void delete(Printer printer) {
		printerJPA.delete(printer);
	}
}
