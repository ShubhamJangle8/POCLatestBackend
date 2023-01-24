package com.ikea.resourceallocation.requestscrud.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import com.ikea.resourceallocation.requestscrud.dto.UserRole;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		System.out.println(jwt.getClaims());
		Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

		if (realmAccess == null || realmAccess.isEmpty()) {
//			System.out.println(realmAccess);
			return new ArrayList<>();
		}

		System.out.println("test" + Arrays.asList(realmAccess.get("roles")));

		for (String role : ((List<String>) realmAccess.get("roles"))) {
			if (role.equals("REQ")) {
				UserRole.getInstance().setReqOrPmo("REQ");

			} else if (role.equals("PMO")) {
				UserRole.getInstance().setReqOrPmo("PMO");
			}
		}

		Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles")).stream()
				.map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		System.out.println("Return Value" + returnValue);
		return returnValue;
	}

}
