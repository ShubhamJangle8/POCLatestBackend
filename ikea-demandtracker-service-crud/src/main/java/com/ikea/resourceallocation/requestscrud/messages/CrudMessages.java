package com.ikea.resourceallocation.requestscrud.messages;

import com.google.common.collect.ImmutableMap;

public class CrudMessages {

	public static ImmutableMap<String, String> message = ImmutableMap.<String, String>builder()
		    .put("SuccessMsg", "Request Created Successfully.") 
		    .put("InsertionFailedMsg", "Request Creation Failed.") 
		    .put("UpdatedMsg", "Request Updated Successfully.") 
		    .put("GradeMsg","Grade Not Found.") 
		    .put("LandedMsg","Landed Data Not Found.") 
		    .put("RequestFoundMsg","Request Fetched Successfully.") 
		    .put("RequestNotFoundMsg", "Request Not Found.") 
		    .put("RequestUpdatedMsg", "Request Updated Successfully.") 
		    .put("RequestNotUpdatedMsg", "Request Updation Failed.")
		    .put("CantWithDrawException", "SO Number Is Already Updated. So Can't Withdraw This Request.")
		    .build();
}
