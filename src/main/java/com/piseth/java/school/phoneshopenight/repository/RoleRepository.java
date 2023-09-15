package com.piseth.java.school.phoneshopenight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.java.school.phoneshopenight.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String username);
	
}
