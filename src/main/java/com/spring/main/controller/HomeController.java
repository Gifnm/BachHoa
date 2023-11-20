package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.util.SessionAttr;

@Controller
public class HomeController {
	// Custom Toast
	static void callToast(String name) {
		// Tự gọi icon hiển thị
		SessionAttr.Toast = name;
		SessionAttr.Icon = name + "__icon";
		SessionAttr.Title = name + "__title";
		SessionAttr.Close = name + "__close";
	}

	@RequestMapping("/sell")
	public String home(Model model) {
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		callToast("success");
		SessionAttr.Show_Icon = SessionAttr.Success_Show_Icon;
		return "pages/sell/sell";
	}

	@RequestMapping("/sell/billsHistory")
	public String bills() {
		return "pages/sell/bills_History";
	}

	@RequestMapping("/sell/productInfomation")
	public String ktGia() {
		return "pages/sell/productDetail";
	}

	@RequestMapping("/sell/endShift")
	public String endShift() {
		return "forward:/sell";
	}

	@RequestMapping("/admin/inventoryHistory")
	public String kiemke() {
		return "pages/admin/inventory_history/inventory_history";
	}

	@RequestMapping("/admin/discountManagement")
	public String discountManagement() {
		return "pages/admin/discount/discountManagement";
	}
}
