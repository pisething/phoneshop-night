package com.piseth.java.school.phoneshopenight.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOriginPatterns("http://localhost:4200", "http://localhost:4201")
		.allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE") // put the http verbs you want allow
		.allowedHeaders("*"); // put the http headers you want allow;
	}
}
