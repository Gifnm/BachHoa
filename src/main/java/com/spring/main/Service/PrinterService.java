package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.main.jpa.PrinterJPA;
import com.spring.main.model.Printers;

@Service
public class PrinterService {
	@Autowired
	PrinterJPA printerJPA;

	public List<Printers> getAllByStoreID(int storeID) {
		List<Printers> list = printerJPA.getAllByStoreID(storeID);
		return list;
	}
public boolean getByIp(String ipAddress) {
	Boolean check = printerJPA.existsById(ipAddress);
	return check;
}
	public void insert(Printers printer) {
		printerJPA.save(printer);
	}

	public void delete(Printers printer) {
		printerJPA.delete(printer);
	}
}
