package com.ikea.resourceallocation.requestscrud.exceptionhandling;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ikea.resourceallocation.requestscrud.customexception.CantWithdrawException;
import com.ikea.resourceallocation.requestscrud.customexception.GradeNotFoundException;
import com.ikea.resourceallocation.requestscrud.customexception.RequestCreaationFailedException;
import com.ikea.resourceallocation.requestscrud.customexception.RequestNotFoundException;
import com.ikea.resourceallocation.requestscrud.exceptionresponse.ExceptionResponse;

@RestControllerAdvice
public class RequestCrudExceptionHandling extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> errors = new HashMap<>();

		errors.put("timeStamp", new Date().toString());
		errors.put("message", "Validation Failed");
		List<Object> err = ex.getBindingResult().getAllErrors().stream().map((error) -> error.getDefaultMessage())
				.collect(Collectors.toList());
		errors.put("details", err);
		return new ResponseEntity<Object>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RequestCreaationFailedException.class)
	public ResponseEntity<Object> nullPointerException(RequestCreaationFailedException e, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setDetails(req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(GradeNotFoundException.class)
	public ResponseEntity<Object> gradeNotFoundException(GradeNotFoundException e, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setDetails(req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RequestNotFoundException.class)
	public ResponseEntity<Object> requestNotFoundException(RequestNotFoundException e, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setDetails(req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CantWithdrawException.class)
	public ResponseEntity<Object> cantWithdrawException(CantWithdrawException e, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setDetails(req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
