package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
@RequestMapping("/home")
public String home() {
	return "pages/sell/sell";
}
@RequestMapping("/products-manager")
public String products() {
	return "pages/products/mgrProducts";
}
}
