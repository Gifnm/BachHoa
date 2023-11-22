package com.spring.main.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.main.Service.EmployeeService;
import com.spring.main.model.CustomEmployeeDetail;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private EmployeeService ES;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		System.out.println("chào mừng quay lại, " + authentication.getName());
		CustomEmployeeDetail userDetails = (CustomEmployeeDetail) authentication.getPrincipal();
		System.out.println("Bạn là: " + userDetails.getAuthorities());
		System.out.println("Nhân viên?: " + userDetails.hasRole("bhoa"));
		System.out.println(userDetails.getStoreWork());

		String redirectURL = request.getContextPath();

		Authentication authentications = SecurityContextHolder.getContext().getAuthentication();

		if (authentications == null || authentications instanceof AnonymousAuthenticationToken) {
			SessionAttr.CURRENT_MESSAGE = "Hãy đăng nhập để tiếp tục bán hàng !";
			redirectURL = "/login";
		} else if(userDetails.getActive() == false && authentications != null ) {
			SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());
			redirectURL = "/business";
		} else if (userDetails.getStoreWork() == null && userDetails.getActive() == false) {
			SessionAttr.USER_INFO = ES.findByEmail(authentication.getName());
			redirectURL = "/business";
		} else {
			if (userDetails.hasRole("qlch")) {
				redirectURL = "/admin/product";
			} else {
				redirectURL = "/login/success";
			}
		}

		response.sendRedirect(redirectURL);
	}

}
