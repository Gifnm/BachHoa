package com.spring.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

	//http.authorizeRequests().antMatchers("/login", "/vendor-login/**", "/css-work/**", "/js-work/**", "/images/**", "/fonts/**").permitAll().anyRequest().authenticated();

		http.formLogin().loginPage("/login").loginProcessingUrl("/login/form")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

		// http.rememberMe().tokenValiditySeconds(86400);

		// http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		// http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/pages/accounts/login");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

		web.httpFirewall(new DefaultHttpFirewall());
	}

}
