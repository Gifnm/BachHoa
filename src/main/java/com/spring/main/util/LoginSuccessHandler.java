package com.spring.main.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.main.model.CustomEmployeeDetail;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

//	@Autowired
//	private EmployeeService service;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		System.out.println("chào mừng quay lại, " + authentication.getName());
		CustomEmployeeDetail userDetails = (CustomEmployeeDetail) authentication.getPrincipal();
		System.out.println("Bạn là: " + userDetails.getAuthorities());
		System.out.println("Nhân viên?: " + userDetails.hasRole("bhoa"));

		String redirectURL = request.getContextPath();

		if (userDetails.hasRole("qlch")) {
			redirectURL = "/admin/product";
		} else {
			redirectURL = "/login/success";
		}

		// Manual with AuthenticationSuccessHandler //
		/*
		 * UrlPathHelper pathHelper = new UrlPathHelper(); String contextPath =
		 * pathHelper.getContextPath(request); response.sendRedirect(contextPath);
		 */

		// Auto with SimpleUrlAuthenticationSuccessHandler //
		// super.onAuthenticationSuccess(request, response, authentication);

		response.sendRedirect(redirectURL);
	}

}
