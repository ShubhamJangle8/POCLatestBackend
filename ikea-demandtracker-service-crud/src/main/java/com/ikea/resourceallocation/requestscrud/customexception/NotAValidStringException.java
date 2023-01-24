package com.ikea.resourceallocation.requestscrud.customexception;

public class NotAValidStringException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String s;

	public NotAValidStringException(String msg) {
		super();
		this.s = msg;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
