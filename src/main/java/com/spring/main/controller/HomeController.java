package com.spring.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.Service.SessionService;

@Controller
public class HomeController {
@Autowired
SessionService sessionService;
	@RequestMapping("/sell")
	public String sell() {
		
		return "pages/sell/sell";
	}
	
	@RequestMapping("/productInfomation")
	public String productInf() {
		return "pages/sell/productDetail";
	}
}
