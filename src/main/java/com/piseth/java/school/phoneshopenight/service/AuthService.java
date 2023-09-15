package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.config.jwt.LoginRequest;
import com.piseth.java.school.phoneshopenight.dto.SignupRequest;

public interface AuthService {
	String createUser(SignupRequest signUpRequest);
	
	String authenticateUser(LoginRequest loginRequest);
}
