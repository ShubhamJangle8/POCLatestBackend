package com.ikea.resourceallocation.requestscrud.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class RequestsForListPageDto {

	private String reqId;

	private String techStack;

	private String gradeId;

	private String lastModifiedDate;

	private String clusterName;

	private String subClusterName;

	private String status;

	private String ownerName;

}
