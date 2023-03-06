package com.ikea.resourceallocation.requestscrud.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreationDto {
	private String reqId;

	@NotNull(message = "Cluster ID Is Null")
	@NotBlank(message = "Cluster ID Is Blank")
	private String clusterId;
	
	private String clusterName;

	@NotNull(message = "Sub Cluster ID Is Null")
	@NotBlank(message = "Sub Cluster ID Is Blank")
	private String subClusterId;

	private String subClusterName;

	@NotNull(message = "Grade ID Is Null")
	@NotBlank(message = "Grade_ID Is Blank")
	private String gradeId;

	@NotNull(message = "Tech Stack Is Null")
	@NotBlank(message = "Tech Stack Is Blank")
	private String techStack;

	@NotNull(message = "Core Skill Is Null")
	@NotBlank(message = "Core Skill Is Blank")
	private String coreSkill;
	
	@NotNull(message = "Project Code Is Null")
	@NotBlank(message = "Project Code Is Blank")
	private String projectCode;
	
	@NotNull(message = "Landed Is Null")
	@NotBlank(message = "Landed Is Blank")
	private String landed;
	
	@NotNull(message = "Reason for Demand Is Null")
	@NotBlank(message = "Reason for Demand Is Blank")
	private String reasonForDemand;
	
	private String ifReplacement;

	@NotNull(message = "Skill Details Is Null")
	@NotBlank(message = "Skill Details Is Blank")
	private String skillDetails;

	@NotNull(message = "Start Date Is Null")
	private String startDate;

	@NotNull(message = "Created Date Is Null")
	private String createdDate;

	private String lastModifiedDate;

	@NotNull(message = "Owner ID Is Null")
	@NotBlank(message = "Owner ID Is Blank")
	private String ownerId;
	
	@NotNull(message = "Owner Name Is Null")
	@NotBlank(message = "Owner Name Is Blank")
	private String ownerName;

	@NotNull(message = "Work Location is Null")
	@NotBlank(message = "Work Location is Blank")
	private String workLocation;

	@NotNull(message = "Area is Null")
	@NotBlank(message = "Area is Blank")
	private String area;
	
	@NotNull(message = "Service Line is Null")
	@NotBlank(message = "Service Line is Blank")
	private String serviceLine;
	
	@NotNull(message = "Bucket Skills is Null")
	@NotBlank(message = "Bucket Skills is Blank")
	private String bucketSkills;

	@NotNull(message = "Status is Null")
	@NotBlank(message = "Status is Blank")
	private String status;
	
	private String soNumber;
	
	private String comment;
	
	@NotNull(message = "Tags is Null")
	//@NotBlank(message = "Tags is Blank")
	private List<String> chips;
	
	private String tags;

	@Override
	public String toString() {
		return "RequestCreationDto [reqId=" + reqId + ", clusterId=" + clusterId + ", clusterName=" + clusterName
				+ ", subClusterId=" + subClusterId + ", subClusterName=" + subClusterName + ", gradeId=" + gradeId
				+ ", techStack=" + techStack + ", coreSkill=" + coreSkill + ", projectCode=" + projectCode + ", landed="
				+ landed + ", reasonForDemand=" + reasonForDemand + ", ifReplacement=" + ifReplacement
				+ ", skillDetails=" + skillDetails + ", startDate=" + startDate + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", ownerId=" + ownerId + ", ownerName=" + ownerName
				+ ", workLocation=" + workLocation + ", area=" + area + ", serviceLine=" + serviceLine
				+ ", bucketSkills=" + bucketSkills + ", status=" + status + ", soNumber=" + soNumber + ", comment="
				+ comment + ", chips=" + chips + ", tags=" + tags + "]";
	}
	
	

}






