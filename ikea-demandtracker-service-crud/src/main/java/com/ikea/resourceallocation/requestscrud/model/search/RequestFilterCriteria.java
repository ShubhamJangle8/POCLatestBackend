package com.ikea.resourceallocation.requestscrud.model.search;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestFilterCriteria {
	
	private String search;

	private List<String> status;

	private List<String> grade;

	private List<String> clus;

	private List<String> subCluster;
	
	private List<String> tags;

	/*
	 * private String status;
	 * 
	 * private String grade;
	 * 
	 * private String clus;
	 * 
	 * private String subCluster;
	 * 
	 * private Date startDate;
	 * 
	 * private Date endDate;
	 */
}
