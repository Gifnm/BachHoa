package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AccountController {
	@RequestMapping("/login")
	public String login() {
		return "pages/account/login";
	}
	
	@RequestMapping("/login/success")
	public ModelAndView loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");		
		return new ModelAndView(new RedirectView("/home", true));
	}
	
	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "pages/account/login";
	}
	
	@RequestMapping("/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "pages/account/login";
	}
	
//	@RequestMapping("/auth/access/denied")
//	public String denied(Model model) {
//		model.addAttribute("message", "Bạn không có quyền truy xuất!");
//		return "security/login";
//	}
}
