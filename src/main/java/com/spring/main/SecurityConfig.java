package com.spring.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
/*import org.springframework.security.authentication.dao.DaoAuthenticationProvider;*/
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import com.spring.main.Service.EmployeeService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new EmployeeService();
	}

	@Bean(name = "passwordEncoder")
	public BCryptPasswordEncoder getPasswordEncoder() {
		/* Đăng nhập mã hóa password Bcrypt */
		return new BCryptPasswordEncoder();
		/* Đăng nhập thẳng bằng mật khẩu thuần không mã hóa */
		// return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder())
			.usersByUsernameQuery(
				"SELECT email, password FROM employees WHERE email = ?")
			.authoritiesByUsernameQuery("SELECT email, roleID FROM employees WHERE email = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/sell/**").authenticated().antMatchers("/admin/**").hasAnyRole("qlch")
			.anyRequest().permitAll();

		http.formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/login/success", false)
			.failureUrl("/login/error").usernameParameter("email").permitAll();

		http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logout/success");
	}

	// cho phep truy xuat REST API tu ben ngoai
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		web.httpFirewall(new DefaultHttpFirewall());
	}

//	@Autowired
//	private LoginSuccessHandler customAuthenticationSuccessHandler;

}
