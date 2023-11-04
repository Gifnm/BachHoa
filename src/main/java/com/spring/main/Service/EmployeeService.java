package com.spring.main.Service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.model.CustomEmployeeDetail;
import com.spring.main.model.Employee;
import com.spring.main.util.EmailUtil;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeJPA employeeJPA;
	@Autowired
	private EmailUtil emailUtil;

	/* Mã hóa password */
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public List<Employee> findAll() {
		List<Employee> list = employeeJPA.findAll();
		return list;
	}

	public void insert(Employee employee) {
		// Gắn mã hóa vào chi tiết của 1 nhân viên (dùng cho những user mới được add sẽ
		// đc mã hóa luôn)
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeJPA.save(employee);
	}

	public void detele(Integer id) {
		employeeJPA.deleteById(id);

	}

	public Employee findByID(Integer id) {
		Employee employee = employeeJPA.findById(id).get();
		return employee;
	}

	public Employee findByEmail(String email) {
		Employee employee = employeeJPA.findbyEmail(email);
		return employee;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeJPA.findbyEmail(email);

		if (employee == null) {
			throw new UsernameNotFoundException("Không tìm thấy nhân viên.");
		}
		return new CustomEmployeeDetail(employee);
	}

	public String verifyAccount(String email, String otp) {
		Employee employee = employeeJPA.findbyEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException("Không tìm thấy nhân viên có email là: " + email);
		}
		try {
			emailUtil.sendOtpEmail(email, otp);
			System.out.println("ĐÃ GỬI TỚI EMAIL: " + email);
		} catch (MessagingException e) {
			throw new RuntimeException("Không thể gửi mã đi, hãy thử lại");
		}
		return "Chú ý, kiểm tra email của bạn để lấy mã otp trong 1 phút.";
	}

	public String forgotPassword(String email) {
		Employee employee = employeeJPA.findbyEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException("Không tìm thấy nhân viên có email là: " + email);
		}
		try {
			emailUtil.sendSetPasswordEmail(email);
			System.out.println("ĐÃ GỬI TỚI EMAIL: " + email);
		} catch (MessagingException e) {
			throw new RuntimeException("Không thể gửi mã đi, hãy thử lại");
		}
		return "Chú ý, kiểm tra email của bạn để thay đổi mật khẩu mới.";
	}

	public String setPassword(String email, String newPassword, String verifyPassword) {
		Employee employee = employeeJPA.findbyEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException("Không tìm thấy nhân viên có email là: " + email);
		}
		if (newPassword.equalsIgnoreCase(verifyPassword)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(newPassword);
			employee.setPassword(encodedPassword);
			employeeJPA.save(employee);
		} else {
			return "Kiểm tra lại mật khẩu không khớp !";
		}
		return "Thay đổi mật khẩu thành công.";
	}

	public boolean userPasswordCheck(String rawPassword, String email) {
		Employee employee = employeeJPA.findbyEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException("Không tìm thấy nhân viên có email là: " + email);
		} else {
			passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = employee.getPassword();
			return passwordEncoder.matches(rawPassword, encodedPassword);
		}
	}

}
