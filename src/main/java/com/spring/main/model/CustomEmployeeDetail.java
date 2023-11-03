package com.spring.main.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomEmployeeDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Employee employee;

	public CustomEmployeeDetail(Employee employee) {
        this.employee = employee;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(employee.getRole().getRoleID().toString());
//		return Arrays.asList(simpleGrantedAuthority); 
		Set<Role> roles = employee.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleID()));
        }
        return authorities;
	}
	
	public boolean hasRole(String roleName) {
        return this.employee.hasRole(roleName);
    }

	@Override
	public String getPassword() {
		// return passwordEncoder.encode(employee.getPassword());
		return employee.getPassword();
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

	public String getFullname() {
		return employee.getEmployeeName();
	}
	
	public Store getStoreWork() {
		return employee.getStore();
	}
	
	public String getPictureURL() {
		return employee.getPictureURL();
	}
	
	public int getID() {
		return employee.getEmployeeID();
	}
	
	public String[] getRoles() {
		return employee.getAuthorities().stream()
			.map(au -> au.getRole().getRoleID())
			.collect(Collectors.toList()).toArray(new String[0]);
	}
	
}
