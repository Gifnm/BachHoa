package com.spring.main.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.main.Service.BillDetailService;
import com.spring.main.Service.ProductService;
import com.spring.main.model.Bill;
import com.spring.main.model.BillDetail;
import com.spring.main.model.Product;

@CrossOrigin("*")
@RestController()
@RequestMapping("/bachhoa/api")
public class BillDetailAPI {
	@Autowired
	BillDetailService billDetailService;
	@Autowired
	ProductService productService;
	// @GetMapping("billDetail/getproduct/{productID}")
	// private BillDetail getProduct(@PathVariable String productID) {
	// System.out.println("Alo kia kia");
	// Product product = productService.getByID(productID);
	// BillDetail billDetail= new BillDetail();
	// billDetail.setQuantity(1);
	// billDetail.setTotalAmount(product.getPrice());
	// billDetail.setProduct(product);
	// System.out.println(billDetail.toString());
	// return billDetail;
	// }
	// @PostMapping("billDetail/create/{listDetail}/{billID}")
	// private void createBillDetail(@PathVariable("listDetail") List<BillDetail>
	// list , @PathVariable("billID") String billID) {
	// Bill bill = new Bill();
	// bill.setBillID(billID);
	// for (BillDetail billDetail : list) {
	// billDetail.setBill(bill);
	// billDetaileService.save(billDetail);
	// }
	//
	// }

	@GetMapping("billDetail/findByBillID/{billID}")
	private List<BillDetail> findByBillID(@PathVariable("billID") String billID) {
		return billDetailService.findByBillID(billID);
	}

	@PostMapping("billDetail/save")
	private BillDetail saveBillDetail(@RequestBody BillDetail billDetail) {
		// System.out.println(billDetail);
		billDetailService.save(billDetail);
		return billDetail;
	}

	// @PostMapping("billDetail/save/{listBillDetail}")
	// private void saveBillDetail(@PathVariable List<BillDetail> listBillDetail) {
	// for(BillDetail billDetail : listBillDetail) {
	// billDetailService.save(billDetail);
	// }
	// }

	@PutMapping("billDetail/update")
	private void updateBillDetail(@RequestBody BillDetail billDetail) {
		billDetailService.save(billDetail);
	}

	@DeleteMapping("billDetail/delete/{billID}/{productID}")
	public void delete(@PathVariable("billID") String billID, @PathVariable("productID") String productID) {
		billDetailService.delete(billID, productID);
	}

}
