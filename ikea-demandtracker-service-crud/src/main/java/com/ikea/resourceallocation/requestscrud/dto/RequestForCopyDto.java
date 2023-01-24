package com.ikea.resourceallocation.requestscrud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestForCopyDto {

	private String grade;
	
	private String selectedGrade;
	
	private String stack;
	
	private String skill;
	
	private String jd;
}
