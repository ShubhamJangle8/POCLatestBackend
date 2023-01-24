package com.ikea.resourceallocation.requestscrud.constants;

import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;

public class Grades {

	private static ImmutableMap<String, String> grades = ImmutableMap
			.of("A4", "Analyst", "A5", "Senior Analyst", "B1",
			"Associate Consultant", "B2", "Consultant", "B3", "Senior Consultant");

	public static Set<String> getAllGrades() {
		Set<String> gradeId = grades.keySet();
		return gradeId;
	}

	public static String getGradeById(String id) {
		String grade = grades.getOrDefault(id.toUpperCase(), CrudMessages.message.get("GradeMsg"));
		return grade;
	}
}
