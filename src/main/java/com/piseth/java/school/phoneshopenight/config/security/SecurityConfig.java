package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.piseth.java.school.phoneshopenight.config.jwt.JwtLoginFilter;
import com.piseth.java.school.phoneshopenight.config.jwt.TokenVerifyFilter;

//@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig {
	/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
			.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers("/","index.html","css/**","js/**").permitAll()
			.antMatchers(HttpMethod.PUT, "/brands/**").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
			//.antMatchers(HttpMethod.PUT, "/brands/**").has(PermissionEnum.BRAND_WRITE.getDescription())
			.anyRequest()
			.authenticated();
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
	*/
}
