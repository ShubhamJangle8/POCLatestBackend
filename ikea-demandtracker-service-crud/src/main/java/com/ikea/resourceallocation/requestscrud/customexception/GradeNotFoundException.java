package com.ikea.resourceallocation.requestscrud.customexception;

public class GradeNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String s;

	public GradeNotFoundException(String msg) {
		super();
		this.s = msg;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
