package com.piseth.java.school.phoneshopenight.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE, BRAND_READ, MODEL_WRITE, MODEL_READ)),
	SALE(Set.of(BRAND_READ, MODEL_READ));
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
		grantedAuthorities.add(role);
		System.out.println(grantedAuthorities);
		return grantedAuthorities;
	}
}
