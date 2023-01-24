package com.ikea.resourceallocation.requestscrud.validation;

import java.util.Date;

/**
 * @author Ubakara Anthony F
 * @since 2021-10-24
 * @version 1.0
 */
public interface Validation {

	/**
	 * @param string
	 * @return TRUE if value is null otherwise FALSE
	 */
	public boolean isNull(String str);

	/**
	 * @param createdDate
	 * @param startDate
	 * @return FALSE if Start Date is less than Request Created Date otherwise TRUE
	 */
	public boolean dateRangeCheck(Date createdDate, Date startDate);

	/**
	 * @param string
	 * @return TRUE if Names contains only Characters otherwise FALSE
	 */
	public boolean checkIfValidString(String str);
}
