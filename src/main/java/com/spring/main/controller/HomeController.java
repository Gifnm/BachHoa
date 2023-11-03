package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.util.SessionAttr;


@Controller
public class HomeController {
	@RequestMapping("/sell")
	public String home(Model model) {
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
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
}
