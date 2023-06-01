package com.piseth.java.school.phoneshopenight.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
	private String jwt;
	private String username;
	private Set<String> roles;
}
