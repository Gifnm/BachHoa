package com.spring.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BachHoaPApplication {

	public static void main(String[] args) {
		SpringApplication.run(BachHoaPApplication.class, args);
		
	}
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/bachhoaimg/**")  // Sử dụng dấu gạch chéo (forward slash) ở đây
	                    .addResourceLocations("file:C:/bachhoaimg/");  // Sử dụng dấu gạch chéo (forward slash) ở đây
	        }
	    };
	}

}
