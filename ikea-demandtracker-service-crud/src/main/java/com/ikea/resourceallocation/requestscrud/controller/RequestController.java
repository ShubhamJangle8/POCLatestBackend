package com.ikea.resourceallocation.requestscrud.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikea.resourceallocation.requestscrud.customexception.RequestCreaationFailedException;
import com.ikea.resourceallocation.requestscrud.dto.RequestCreationDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestCreationResponseDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestForCopyDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestsForListPageDto;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;
import com.ikea.resourceallocation.requestscrud.model.search.RequestFilterCriteria;
import com.ikea.resourceallocation.requestscrud.service.RequestService;
import com.ikea.resourceallocation.requestscrud.service.impl.ExcelExporter;
import com.ikea.resourceallocation.requestscrud.service.impl.PDFExporter;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/req")
public class RequestController {

	@Autowired
	private RequestService requestService;

	@PostMapping("/create")
	public ResponseEntity<RequestCreationResponseDto> createRequest(
			@Valid @RequestBody RequestCreationDto requestCreationDto) {
		RequestCreationResponseDto req = requestService.createRequest(requestCreationDto);
		if (!req.equals(null)) {
			return new ResponseEntity<RequestCreationResponseDto>(req, HttpStatus.CREATED);
		} else {
			throw new RequestCreaationFailedException(CrudMessages.message.get("InsertionFailedMsg"));
		}
	}

//	@GetMapping("/getAll")
//	public ResponseEntity<List<RequestsForListPageDto>> getAllRequests() {
//		List<RequestsForListPageDto> allRequestsForListPage = requestService.getAllRequests();
//		return new ResponseEntity<>(allRequestsForListPage, HttpStatus.OK);
//	}

	@PostMapping("/getAll")
	public ResponseEntity<List<RequestsForListPageDto>> getAllRequestsWithFilter(
			@RequestBody RequestFilterCriteria criteria) {
		System.out.println(criteria);
		List<RequestsForListPageDto> allRequestsForListPage = requestService.getAllRequestsWithFilters(criteria);
		return new ResponseEntity<>(allRequestsForListPage, HttpStatus.OK);
	}

	@GetMapping("/get/{reqId}")
	public ResponseEntity<RequestCreationResponseDto> getRequestById(@PathVariable String reqId) {
		RequestCreationResponseDto requestCreationResponseDto = requestService.getRequestById(reqId);
		System.out.println(reqId);
		return new ResponseEntity<>(requestCreationResponseDto, HttpStatus.OK);
	}

	@GetMapping("/get/{reqId}/copy")
	public ResponseEntity<RequestForCopyDto> getRequestForCopyById(@PathVariable String reqId) {
		RequestForCopyDto copyDto = requestService.getDataForCopyOptionById(reqId);
		return new ResponseEntity<>(copyDto, HttpStatus.OK);
	}

	@PutMapping("/upd")
	public ResponseEntity<RequestCreationResponseDto> updateRequest(@RequestBody RequestCreationDto requestCreationDto)
			throws Exception {
		RequestCreationResponseDto req = requestService.updateRequest(requestCreationDto);
		if (!req.equals(null)) {
			return new ResponseEntity<RequestCreationResponseDto>(req, HttpStatus.OK);
		} else {
			throw new RequestCreaationFailedException(CrudMessages.message.get("InsertionFailedMsg"));
		}
	}
	
	@PostMapping("/export/excel")
    public ResponseEntity exportToExcel(HttpServletResponse response, @RequestBody List<RequestCreationDto> requestListCreationDto) throws IOException {
		response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Request_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        ExcelExporter excelExporter = new ExcelExporter(requestListCreationDto);
         
        excelExporter.export(response);    
        
        return ResponseEntity.ok().build();
    }  
	
	@PostMapping("/export/pdf")
    public ResponseEntity exportToPdf(HttpServletResponse response, @RequestBody List<RequestCreationDto> requestListCreationDto) throws IOException {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        PDFExporter exporter = new PDFExporter(requestListCreationDto);
        exporter.export(response);
        
        return ResponseEntity.ok().build();
    }  
}
