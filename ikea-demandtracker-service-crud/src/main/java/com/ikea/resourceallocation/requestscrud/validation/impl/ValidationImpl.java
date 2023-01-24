package com.ikea.resourceallocation.requestscrud.validation.impl;

import java.util.Date;

import com.ikea.resourceallocation.requestscrud.validation.Validation;

/**
 * @author Nidhi Rao
 * @since 27-10-2021
 * @version 1.0
 */
public class ValidationImpl implements Validation {

	/**
	 * @param str
	 * @return TRUE if string is not null, otherwise FALSE
	 */
	public boolean isNull(String str) {
		if (str.trim().equals(null)) {
			return false;
		}
		return true;
	}

	/**
	 * @param createdDate
	 * @param startDate
	 * @return FALSE if Start Date is less than Request Created Date otherwise TRUE
	 */
	public boolean dateRangeCheck(Date createdDate, Date startDate) {
		if (createdDate.before(startDate)) {
			return true;
		}
		return false;
	}

	/**
	 * @param str
	 * @return TRUE if Names contains only Characters otherwise FALSE
	 */
	public boolean checkIfValidString(String str) {
		if (str.matches("^[a-zA-Z ]*$")) {
			return true;
		}
		return false;
	}

}
