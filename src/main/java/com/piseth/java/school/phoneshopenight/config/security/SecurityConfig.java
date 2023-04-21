package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.piseth.java.school.phoneshopenight.config.jwt.JwtLoginFilter;
import com.piseth.java.school.phoneshopenight.config.jwt.TokenVerifyFilter;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilter(new JwtLoginFilter(authenticationManager()))
			.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers("/","index.html","css/**","js/**").permitAll()
			//.antMatchers("/models").hasRole(RoleEnum.SALE.name()) // "SALE"
			//.antMatchers(HttpMethod.POST, "/brands").hasAuthority(BRAND_WRITE.getDescription())
			//.antMatchers(HttpMethod.GET, "/brands").hasAuthority(BRAND_READ.getDescription())
			.anyRequest()
			.authenticated();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		//User user1 = new User("dara", passwordEncoder.encode("dara123"), Collections.emptyList());
		
		UserDetails user1 = User.builder()
				.username("dara")
				.password(passwordEncoder.encode("dara123"))
				//.roles(RoleEnum.SALE.name()) //ROLE_SALE
				.authorities(RoleEnum.SALE.getAuthorities()) // collection of GrantedAuthority
				.build();
		//GrantedAuthority
		
		UserDetails user2 = User.builder()
				.username("thida")
				.password(passwordEncoder.encode("thida123"))
				.authorities(RoleEnum.ADMIN.getAuthorities()) // ROLE_ADMIN
				.build();
		
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1, user2);
		
		return userDetailsService;
	}
}
