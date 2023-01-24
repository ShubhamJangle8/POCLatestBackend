package com.ikea.resourceallocation.requestscrud.customexception;

import org.springframework.stereotype.Component;

public class DateRangeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String s;

	public DateRangeException(String msg) {
		super();
		this.s = msg;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
