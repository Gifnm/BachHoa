package com.spring.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.Service.EmployeeService;
import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.model.Employee;
import com.spring.main.util.SessionAttr;
import com.spring.main.util.SessionService;

import net.bytebuddy.utility.RandomString;

@Controller
public class AccountController {
	@Autowired
	private EmployeeService ES;
	@Autowired
	private EmployeeJPA employeeJPA;
	@Autowired
	SessionService sessionService;
	
	String Random_otp;
	
	static String OTP(int len) {
		String otp = RandomString.make(len);
		return otp;
	}
	
	@RequestMapping("/login")
	public String showlogin(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
			return "pages/account/login";
		} else {
			SessionAttr.CURRENT_MESSAGE = "Chào mừng quay lại !";
			return "redirect:/sell";
		}
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Đăng nhập thành công!";
		return "redirect:/sell";
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Sai thông tin đăng nhập!";
		return "redirect:/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Chúc bạn 1 ngày tốt lành !";
		return "redirect:/login";
	}

	@RequestMapping("/auth/access/denied")
	public String denied(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Bạn không có quyền truy xuất!";
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		return "redirect:/sell";
	}

	@RequestMapping("/regenerate-otp")
	public String regenerateOTP(@RequestParam("R_Email") String email, Model model) {
		Employee employee = employeeJPA.findbyEmail(email);
		try {
			if (employee == null) {
				SessionAttr.CURRENT_MESSAGE = "Không tìm thấy nhân viên có email là: " + email;
				return "redirect:/login";
			} else {
				Random_otp = OTP(8);
				model.addAttribute("message","Hãy check email để lấy mã OTP");
				ES.verifyAccount(email, Random_otp);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		sessionService.set("email", email);
		return "pages/account/vertifyOTP";
	}

	@RequestMapping("/verify-account")
	public String forgotPassword(@RequestParam("OTP") String otp, Model model) {
		SessionAttr.CURRENT_MESSAGE = "Hãy check email để thay đổi mật khẩu";
		if (otp.equalsIgnoreCase(Random_otp)) {
			String email = sessionService.get("email");
			ES.forgotPassword(email);
			// System.out.println("Email: "+ email);
		}
		System.out.println("Your OTP: "+ otp + ", and generate otp: " + Random_otp);
		return "redirect:/login";
	}

	@GetMapping("/set-password")
	public String DisplaysetPassword(Model model) {
		model.addAttribute("message", "Đừng quên mật khẩu của bạn nữa nhé.");
		return "pages/account/newPass";
	}
	
	@PostMapping("/set-password")
	public String setPassword(Model model, @RequestParam("new_Password") String newPass, @RequestParam("verify_Password") String VerifyPass) {
		String email = sessionService.get("email");
		ES.setPassword(email, newPass, VerifyPass);
		SessionAttr.CURRENT_MESSAGE = "Thay đổi mật khẩu thành công !";
		return "redirect:/login";
	}
	
}
