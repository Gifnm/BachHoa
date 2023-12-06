package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.DeliveryNoteService;
import com.spring.main.Service.DetailedDeliveryNoteService;
import com.spring.main.Service.ProductService;
import com.spring.main.Service.PurchaseHistoryService;
import com.spring.main.Service.EmployeeService;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.Product;
import com.spring.main.model.PurchaseHistory;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/detaildeliverynote/")
public class DetailedDeliveryNoteAPI {
	@Autowired
	DetailedDeliveryNoteService deliveryNoteService;
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	@Autowired
	ProductService productService;
	@Autowired
	DeliveryNoteService deliveService;
	@Autowired
	EmployeeService employeeService;

	@GetMapping("getall/{deliveryNoteId}")
	private ResponseEntity<List<DetailedDeliveryNote>> getAll(@PathVariable("deliveryNoteId") String id) {
		System.out.println("getall/{deliveryNoteId}");
		List<DetailedDeliveryNote> list = deliveryNoteService.getall(id);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		else {
			System.out.println("Danh sach phieu cham hang chi tiet: " + list.size());
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}

	}

	@PostMapping("save")
	private void save(@RequestPart("list") List<DetailedDeliveryNote> list, @RequestPart("employeeID") int employeeID) {
		for (DetailedDeliveryNote detailedDeliveryNote : list) {
			PurchaseHistory purchaseHistory = new PurchaseHistory();
			purchaseHistory.setId(detailedDeliveryNote.getId());
			purchaseHistory.setProductID(detailedDeliveryNote.getProductID());
			purchaseHistory.setDeliveryNote(detailedDeliveryNote.getDeliveryNote());
			purchaseHistory.setProduct(detailedDeliveryNote.getProduct());
			purchaseHistory.setSysInventory(detailedDeliveryNote.getProduct().getInventory());
			purchaseHistory.setQuantityReceived(detailedDeliveryNote.getQuantity());
			purchaseHistory.setConfirmedQuantity(detailedDeliveryNote.getCount());
			purchaseHistory.setEmployee(employeeService.findByID(employeeID));
			purchaseHistoryService.save(purchaseHistory);
			Product product = detailedDeliveryNote.getProduct();
			product.setInventory(product.getInventory() + detailedDeliveryNote.getCount());
			productService.update(product);
			// Xoa phieu xuat chi tiet
			deliveryNoteService.delete(detailedDeliveryNote);
		}
		deliveService.setFinish(list.get(0).getId());

	}
}
