package com.ikea.resourceallocation.requestscrud.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikea.resourceallocation.requestscrud.constants.Grades;
import com.ikea.resourceallocation.requestscrud.customexception.GradeNotFoundException;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/req/grade")
public class GradesController {

	@GetMapping("/getAll")
	public ResponseEntity<Set<String>> getAllGradesId() {
		Set<String> gradeIds = Grades.getAllGrades();
		return new ResponseEntity<>(gradeIds, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<String> getGradeTypeById(@PathVariable String id) {
		String grade = Grades.getGradeById(id);
		return new ResponseEntity<String>(grade, HttpStatus.OK);
	}
}
