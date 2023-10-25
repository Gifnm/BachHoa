package com.spring.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.main.Service.EmployeeService;
import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.model.Employee;
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
	
	@GetMapping("/login")
	public String showlogin(Model model) {
		model.addAttribute("message", "Chào mừng quay lại !");
		model.addAttribute("color","info");
		return "pages/account/login";
	}

	@RequestMapping("/login/success")
	public ModelAndView loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		model.addAttribute("color","success");
		return new ModelAndView(new RedirectView("/home", true));
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		model.addAttribute("color","danger");
		return "pages/account/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		model.addAttribute("color","success");
		return "pages/account/login";
	}

	@RequestMapping("/auth/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		model.addAttribute("color","danger");
		return "errorPage";
	}

	@RequestMapping("/regenerate-otp")
	public String regenerateOTP(@RequestParam("R_Email") String email, Model model) {
		Employee employee = employeeJPA.findbyEmail(email);
		try {
			if (employee == null) {
				model.addAttribute("message","Không tìm thấy nhân viên có email là: " + email);
				model.addAttribute("color","danger");
				return "redirect:/login";
			} else {
				Random_otp = OTP(8);
				// System.out.println("Your email: " + email + ", Your OTP: "+Random_otp);
				model.addAttribute("message","Hãy check email để lấy mã OTP");
				model.addAttribute("color","info");
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
		model.addAttribute("message","Hãy check email để thay đổi mật khẩu");
		model.addAttribute("color","warning");
		if (otp.equalsIgnoreCase(Random_otp)) {
			String email = sessionService.get("email");
			ES.forgotPassword(email);
			// System.out.println("Email: "+ email);
		}
		System.out.println("Your OTP: "+ otp + ", and generate otp: " + Random_otp);
		return "pages/account/login";
	}

	@GetMapping("/set-password")
	public String DisplaysetPassword(Model model) {
		model.addAttribute("color","warning");
		model.addAttribute("message", "Đừng quên mật khẩu của bạn nữa nhé.");
		return "pages/account/newPass";
	}
	
	@PostMapping("/set-password")
	public String setPassword(Model model, @RequestParam("new_Password") String newPass, @RequestParam("verify_Password") String VerifyPass) {
		String email = sessionService.get("email");
		ES.setPassword(email, newPass, VerifyPass);
		model.addAttribute("color","info");
		return "redirect:/login";
	}
}
