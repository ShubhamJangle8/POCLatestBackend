package com.ikea.resourceallocation.requestscrud.customexception;

public class RequestNotUpdateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String s;

	public RequestNotUpdateException(String msg) {
		super();
		this.s = msg;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
