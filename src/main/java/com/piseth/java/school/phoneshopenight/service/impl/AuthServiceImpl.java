package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.jwt.LoginRequest;
import com.piseth.java.school.phoneshopenight.config.security.AuthUser;
import com.piseth.java.school.phoneshopenight.config.security.JwtUtils;
import com.piseth.java.school.phoneshopenight.config.security.RoleEnum;
import com.piseth.java.school.phoneshopenight.dto.SignupRequest;
import com.piseth.java.school.phoneshopenight.entity.Role;
import com.piseth.java.school.phoneshopenight.entity.User;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.repository.RoleRepository;
import com.piseth.java.school.phoneshopenight.repository.UserRepository;
import com.piseth.java.school.phoneshopenight.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtUtils jwtUtils;

	@Override
	public String createUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already taken!");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleEnum.USER.name())
					.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Role is not found!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				Role adminRole = roleRepository.findByName(role)
						.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, role + " Role is not found!"));
				roles.add(adminRole);
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return jwtUtils.generateJwtToken(signUpRequest.getUsername());
	}

	@Override
	public String authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    AuthUser userPrincipal = (AuthUser) authentication.getPrincipal();

		    return jwtUtils.generateJwtToken(userPrincipal.getUsername());
	}

}
