package com.ikea.resourceallocation.requestscrud.customexception;

public class RequestNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String s;

	public RequestNotFoundException(String msg) {
		super();
		this.s = msg;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
