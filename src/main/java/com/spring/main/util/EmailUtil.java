package com.spring.main.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.main.jpa.MailerJPA;

import javax.mail.MessagingException;

@Component
public class EmailUtil {
	@Autowired
	MailerJPA mailer;

	public void sendOtpEmail(String email, String otp) throws MessagingException {
		mailer.send(email, "Xác minh - OTP",
				"<h1>🛒 BachHoa<h1/> <h2>Mã xác nhận của bạn là: <h2/><h1 style='color: red'>" + otp + "<h1/>");
	}

	public void sendSetPasswordEmail(String email) throws MessagingException {
		mailer.send(email, "Thay đổi mật khẩu của bạn",
				"<p> Xin chào, Bạn đã yêu cầu thay đổi mật khẩu </p>"
						+ "<h3 style='color: cadetblue'> Nhấn nút bên dưới để bắt đầu </h3>"
						+ "<div> <a href='http://localhost:8083/set-password' target='_blank'> Bấm vào đây để đổi mật khẩu.</a> </div>");
	}
}
