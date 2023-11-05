package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.BillDetaileService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.Bill;
import com.spring.main.model.BillDetail;
import com.spring.main.model.Product;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class BillDetailAPI {
@Autowired 
BillDetaileService billDetaileService;
@Autowired
ProductService productService;
@GetMapping("billDetail/getproduct/{productID}")
private BillDetail getProduct(@PathVariable String productID) {
	// product = productService.getByID(productID);
	BillDetail billDetail= new BillDetail();
	billDetail.setQuantity(1);
//	billDetail.setTotalAmount(product.getPrice());
//	billDetail.setProduct(product);
	System.out.println(billDetail.toString());
	return billDetail;
}
@PostMapping("billDetail/create/{listDetail}/{billID}")
private void createBillDetail(@PathVariable("listDetail") List<BillDetail> list , @PathVariable("billID") String billID) {
	Bill bill = new Bill();
	bill.setBillID(billID);
	for (BillDetail billDetail : list) {
		//billDetail.setBill(bill);
		billDetaileService.createBillDetail(billDetail);
	}
	
}
@GetMapping("hihi")
private void save() {
	BillDetail billDetail = new BillDetail();
	billDetail.setBillID("111");
	billDetail.setProductID("");
	
}


}
