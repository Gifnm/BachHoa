package com.spring.main.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomEmployeeDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* Mã hóa password */
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Employee employee;

	public CustomEmployeeDetail(Employee employee) {
        this.employee = employee;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(employee.getRole().toString());
		return Arrays.asList(simpleGrantedAuthority); 
	}

	// Gắn mã hóa vào chi tiết của 1 nhân viên (dùng tạm cho những user đã tạo sẳn)
	@Override
	public String getPassword() {
		return passwordEncoder.encode(employee.getPassword());
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return employee.getEmployeeName();
	}
}
