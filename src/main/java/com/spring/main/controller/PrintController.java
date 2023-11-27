package com.spring.main.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.main.Service.BillDetailService;
import com.spring.main.Service.BillService;
import com.spring.main.model.Bill;
import com.spring.main.model.BillDetail;
import com.spring.main.model.Invoice;
import com.spring.main.util.SessionAttr;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class PrintController {
	@Autowired
	BillDetailService billDetailService;
	@Autowired
	BillService billService;

	// Custom Toast
	static void callToast(String name) {
		// Tự gọi icon hiển thị
		SessionAttr.Toast = name;
		SessionAttr.Icon = name + "__icon";
		SessionAttr.Title = name + "__title";
		SessionAttr.Close = name + "__close";
	}

	@GetMapping("/print/{billID}")
	public ResponseEntity<byte[]> print(Model model, @PathVariable("billID") String billID)
			throws FileNotFoundException, JRException {
		Bill bill = billService.findByID(billID);
		List<BillDetail> billDetail = billDetailService.findByBillID(bill.getBillID());
		List<Invoice> list = new ArrayList<>();
		for (BillDetail item : billDetail) {
			Invoice invoice = new Invoice();
			invoice.setProductName(item.getProduct().getProductName());
			invoice.setQuantity(item.getQuantity());
			float vat = (float) item.getProduct().getVat() / 100;
			float price = item.getProduct().getPrice() + (item.getProduct().getPrice() * vat);
			invoice.setPrice(formatNumber(Math.round(price)));
			invoice.setTotalAmount(formatNumber(Math.round(item.getTotalAmount())));
			list.add(invoice);
		}
		for (BillDetail item : billDetail) {
			if (item.getQuantityGift() > 0) {
				Invoice invoiceGift = new Invoice();
				invoiceGift.setProductName("Quà tặng: " + item.getProduct().getProductName());
				invoiceGift.setQuantity(item.getQuantityGift());
				invoiceGift.setPrice("0");
				invoiceGift.setTotalAmount("0");
				list.add(invoiceGift);
			}
		}
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("billCode", bill.getBillID());
		Timestamp now = bill.getTimeCreate();
		Date date = new Date(now.getTime());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateString = df.format(date);
		map.put("createDate", dateString);
		map.put("amountReceivable", formatNumber(Math.round(bill.getTotalAmount())));
		map.put("cashRound", formatNumber(roundToThousand(Math.round(bill.getTotalAmount()))));
		map.put("cash", formatNumber(Math.round(bill.getCash())));
		map.put("change",
				formatNumber(Math.round(bill.getCash()) - roundToThousand(Math.round(bill.getTotalAmount()))));
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(report, "invoice.pdf");
		byte[] data = JasperExportManager.exportReportToPdf(report);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice.pdf");
		
		// Thông báo
		SessionAttr.CURRENT_MESSAGE = "Xuất hóa đơn thành công !";
		callToast("success");
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		// return "ok";
	}

	public static int roundToThousand(float value) {
		double scale = 1000;
		return (int) (Math.round(value / scale) * scale);
	}

	public static String formatNumber(int value) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(value);
	}

}
