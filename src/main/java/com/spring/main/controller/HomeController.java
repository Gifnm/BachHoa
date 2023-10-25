package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/sell")
	public String home() {
		return "pages/sell/sell";
	}
	
	@RequestMapping("/productInfomation")
	public String productInf() {
		return "pages/sell/productDetail";
	}
}
