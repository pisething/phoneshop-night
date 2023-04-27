package com.piseth.java.school.phoneshopenight.config.security;

import java.util.Optional;

public interface UserService {
	Optional<AuthUser> findUserByUsername(String username);
}
