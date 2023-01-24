package com.ikea.resourceallocation.requestscrud.service;

import java.util.List;

import com.ikea.resourceallocation.requestscrud.dto.RequestCreationDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestCreationResponseDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestForCopyDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestsForListPageDto;
import com.ikea.resourceallocation.requestscrud.model.Request;
import com.ikea.resourceallocation.requestscrud.model.search.RequestFilterCriteria;

/**
 * @author Ubakara Anthony F
 * @since 2021-10-24
 * @version 1.0
 */
public interface RequestService {

	/**
	 * @param request
	 * @return TRUE if data inserted into DB otherwise FALSE
	 */
	public RequestCreationResponseDto createRequest(RequestCreationDto requestCreationDto);

	public List<RequestsForListPageDto> getAllRequests();

	public RequestCreationResponseDto getRequestById(String reqId);

	public RequestCreationResponseDto updateRequest(RequestCreationDto requestCreationDto);

	public List<RequestsForListPageDto> getAllRequestsWithFilters(RequestFilterCriteria requestFilterCriteria);
	
	public RequestForCopyDto getDataForCopyOptionById(String reqId);

}
