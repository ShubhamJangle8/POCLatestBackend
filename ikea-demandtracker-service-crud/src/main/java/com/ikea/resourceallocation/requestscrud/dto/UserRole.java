package com.ikea.resourceallocation.requestscrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole {

	private static UserRole instance;

	private UserRole() {

	}

	public static UserRole getInstance() {
		if (instance == null) {
			instance = new UserRole();
		}
		return instance;
	}

	private String reqOrPmo;
}
