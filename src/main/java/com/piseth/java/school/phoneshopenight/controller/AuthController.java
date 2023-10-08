package com.piseth.java.school.phoneshopenight.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.config.jwt.LoginRequest;
import com.piseth.java.school.phoneshopenight.dto.SignupRequest;
import com.piseth.java.school.phoneshopenight.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String jwt = authService.authenticateUser(loginRequest);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + jwt);
		responseHeaders.set("Access-Control-Expose-Headers", "Authorization");
		
		return ResponseEntity.ok().headers(responseHeaders).build();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		String jwt = authService.createUser(signUpRequest);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + jwt);
		return ResponseEntity.ok().headers(responseHeaders).build();
	}

}
