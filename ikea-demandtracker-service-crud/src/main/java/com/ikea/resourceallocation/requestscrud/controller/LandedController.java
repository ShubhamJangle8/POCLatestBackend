package com.ikea.resourceallocation.requestscrud.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikea.resourceallocation.requestscrud.constants.Landed;
import com.ikea.resourceallocation.requestscrud.customexception.LandedNotFoundException;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/req/landed")
public class LandedController {

	@GetMapping("/getAll")
	public ResponseEntity<Set<String>> getAllLandedId() {
		Set<String> landedIds = new HashSet<>(Landed.getAllLandedData());
		return new ResponseEntity<>(landedIds, HttpStatus.OK);

	}

//	@GetMapping("/get/{id}")
//	public ResponseEntity<String> getLandedTypeById(@PathVariable String id) {
//		String landed = Landed.getLandedById(id);
//		return new ResponseEntity<String>(landed, HttpStatus.OK);
//	}
}
