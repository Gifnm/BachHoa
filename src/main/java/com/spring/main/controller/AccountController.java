package com.spring.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.main.Service.EmployeeService;
import com.spring.main.model.Employee;
import com.spring.main.util.SessionAttr;
import com.spring.main.util.SessionService;

import net.bytebuddy.utility.RandomString;

@Controller
public class AccountController {
	@Autowired
	private EmployeeService ES;
	@Autowired
	SessionService sessionService;

	String Random_otp;

	static String OTP(int len) {
		String otp = RandomString.make(len);
		return otp;
	}

	// Custom Toast
	static void callToast(String name) {
		// Tự gọi icon hiển thị
		SessionAttr.Toast = name;
		SessionAttr.Icon = name + "__icon";
		SessionAttr.Title = name + "__title";
		SessionAttr.Close = name + "__close";
	}

	@RequestMapping("/business")
	public String showRegister(Model model) {
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		model.addAttribute("employee", SessionAttr.USER_INFO);
		System.out.println(SessionAttr.USER_INFO);
		return "pages/account/Register";
	}

	@RequestMapping("/business/load")
	public String loadInfo(Model model, Authentication authentication) {
		SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());
		model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		model.addAttribute("employee", SessionAttr.USER_INFO);
		// System.out.println(SessionAttr.USER_INFO);
		return "redirect:/business";
	}

	@RequestMapping("/register")
	public String RegisterAccount() {
		SessionAttr.CURRENT_MESSAGE = "Đăng ký thành công !";
		callToast("success");
		SessionAttr.Show_Icon = SessionAttr.Success_Show_Icon;
		// Gắn lại account
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showlogin(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken
			|| SessionAttr.USER_INFO.isActive() == false) {
			model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
			// Toast - Thông báo
			model.addAttribute("Toast", SessionAttr.Toast);
			model.addAttribute("Toast_icon", SessionAttr.Icon);
			model.addAttribute("Toast_show_icon", SessionAttr.Show_Icon);
			model.addAttribute("Toast_title", SessionAttr.Title);
			model.addAttribute("Toast_close", SessionAttr.Close);
			return "pages/account/Login";
		}
		// SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());

		SessionAttr.CURRENT_MESSAGE = "Đã đăng nhập rồi !";
		// Toast - Thông báo
		model.addAttribute("Toast", SessionAttr.Toast);
		model.addAttribute("Toast_icon", SessionAttr.Icon);
		model.addAttribute("Toast_show_icon", SessionAttr.Show_Icon);
		model.addAttribute("Toast_title", SessionAttr.Title);
		model.addAttribute("Toast_close", SessionAttr.Close);
		return "redirect:/sell";
	}

	@PostMapping("/login")
	public String LoginWork(Model model, @PathVariable("email") String email) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());

		if (ES.CheckStore(email) == true) {
			model.addAttribute("employee", ES.findByEmail(email));
			return "redirect:/business";
		} else {
			return "redirect:/sell";
		}
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Đăng nhập thành công !";
		callToast("success");
		SessionAttr.Show_Icon = SessionAttr.Success_Show_Icon;
		return "redirect:/sell";
	}

	@GetMapping("/login/error")
	public String loginError(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Email hoặc mật khẩu không đúng !";
		// model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		callToast("error");
		SessionAttr.Show_Icon = SessionAttr.Error_Show_Icon;
		return "redirect:/login";
	}

	@PostMapping("/login/error")
	public String loginErrorCheck(Model model, @RequestParam("email") String email) {
		Employee employee = ES.findByEmail(email);
		try {
			if (employee == null) {
				SessionAttr.CURRENT_MESSAGE = "Tài khoản không tồn tại !";
			} else {
				SessionAttr.CURRENT_MESSAGE = "Email hoặc mật khẩu không đúng !";
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		// model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		return "redirect:/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Chúc bạn 1 ngày tốt lành !";
		// model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		callToast("info");
		SessionAttr.Show_Icon = SessionAttr.Info_Show_Icon;
		return "redirect:/login";
	}

	@RequestMapping("/auth/access/denied")
	public String denied(Model model) {
		SessionAttr.CURRENT_MESSAGE = "Bạn không có quyền truy xuất !";
		// model.addAttribute("message", SessionAttr.CURRENT_MESSAGE);
		callToast("warning");
		SessionAttr.Show_Icon = SessionAttr.Warning_Show_Icon;
		return "redirect:/sell";
	}

	@RequestMapping("/regenerate-otp")
	public String regenerateOTP(@RequestParam("R_Email") String email, Model model) {
		Employee employee = ES.findByEmail(email);
		try {
			if (employee == null) {
				SessionAttr.CURRENT_MESSAGE = "Không tìm thấy nhân viên có email là: " + email;
				callToast("error");
				SessionAttr.Show_Icon = SessionAttr.Error_Show_Icon;
				return "redirect:/login";
			} else {
				Random_otp = OTP(8);
				model.addAttribute("message", "Hãy check email để lấy mã OTP");
				ES.verifyAccount(email, Random_otp);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		callToast("success");
		SessionAttr.Show_Icon = SessionAttr.Success_Show_Icon;
		sessionService.set("email", email);
		return "pages/account/vertifyOTP";
	}

	@RequestMapping("/verify-account")
	public String forgotPassword(@RequestParam("OTP") String otp, Model model) {
		SessionAttr.CURRENT_MESSAGE = "Hãy check email để thay đổi mật khẩu";
		callToast("info");
		SessionAttr.Show_Icon = SessionAttr.Info_Show_Icon;
		if (otp.equalsIgnoreCase(Random_otp)) {
			String email = sessionService.get("email");
			ES.forgotPassword(email);
			// System.out.println("Email: "+ email);
		}
		System.out.println("Your OTP: " + otp + ", and generate otp: " + Random_otp);
		return "redirect:/login";
	}

	@GetMapping("/set-password")
	public String DisplaysetPassword(Model model) {
		model.addAttribute("message", "Đừng quên mật khẩu của bạn nữa nhé.");
		callToast("warning");
		SessionAttr.Show_Icon = SessionAttr.Warning_Show_Icon;
		return "pages/account/newPass";
	}

	@PostMapping("/set-password")
	public String setPassword(Model model, @RequestParam("new_Password") String newPass,
		@RequestParam("verify_Password") String VerifyPass) {
		String email = sessionService.get("email");
		ES.setPassword(email, newPass, VerifyPass);
		SessionAttr.CURRENT_MESSAGE = "Thay đổi mật khẩu thành công !";
		callToast("success");
		SessionAttr.Show_Icon = SessionAttr.Success_Show_Icon;
		return "redirect:/login";
	}
}
