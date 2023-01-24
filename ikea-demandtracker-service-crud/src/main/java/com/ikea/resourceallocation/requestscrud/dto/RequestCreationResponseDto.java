package com.ikea.resourceallocation.requestscrud.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreationResponseDto {

	private String reqId;

	private String clusterId;

	private String clusterName;

	private String subClusterId;

	private String subClusterName;

	private String gradeId;

	private String grade;

	private String techStack;

	private String coreSkill;
	
	private String projectCode;
	
	private String landed;
	
	private String reasonForDemand;
	
	private String ifReplacement;

	private String skillDetails;

	private String startDate;

	private String createdDate;

	private String ownerId;
	
	private String ownerName;

	private String workLocation;

	private String area;
	
	private String serviceLine;
	
	private String bucketSkills;

	private String soNumber;

	private String status;

	private String comment;
	
//	private List<String> tags;

	private String toastrMessage;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	private RequestCreationResponseDto(RequestCreationResponseDtoBuilder builder) {
		this.reqId = builder.reqId;
		this.clusterId = builder.clusterId;
		this.clusterName = builder.clusterName;
		this.subClusterId = builder.subClusterId;
		this.subClusterName = builder.subClusterName;
		this.gradeId = builder.gradeId;
		this.grade = builder.grade;
		this.techStack = builder.techStack;
		this.coreSkill = builder.coreSkill;
		this.projectCode = builder.projectCode;
		this.landed = builder.landed;
		this.reasonForDemand = builder.reasonForDemand;
		this.ifReplacement = builder.ifReplacement;
		this.skillDetails = builder.skillDetails;
		this.startDate = builder.startDate;
		this.createdDate = builder.createdDate;
		this.ownerId = builder.ownerId;
		this.ownerName = builder.ownerName;
		this.workLocation = builder.workLocation;
		this.area = builder.area;
		this.serviceLine = builder.serviceLine;
		this.bucketSkills = builder.bucketSkills;
		this.soNumber = builder.soNumber;
		this.status = builder.status;
		this.comment = builder.comment;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
//		this.tags = builder.tags;
		this.toastrMessage = builder.toastrMessage;
	}

	public static class RequestCreationResponseDtoBuilder {
		private String reqId;

		private String clusterId;

		private String clusterName;

		private String subClusterId;

		private String subClusterName;

		private String gradeId;

		private String grade;

		private String techStack;

		private String coreSkill;
		
		private String projectCode;
		
		private String landed;
		
		private String reasonForDemand;
		
		private String ifReplacement;

		private String skillDetails;

//		@JsonFormat(pattern = "yyyy-MM-dd")
		private String startDate;

//		@JsonFormat(pattern = "yyyy-MM-dd")
		private String createdDate;

		private String ownerId;
		
		private String ownerName;

		private String workLocation;

		private String area;
		
		private String serviceLine;
		
		private String bucketSkills;

		private String soNumber;

		private String status;

		private String comment;
		
//		private List<String> tags;

		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;

		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date updatedAt;

		private String toastrMessage;

		public RequestCreationResponseDtoBuilder setReqId(String reqId) {
			this.reqId = reqId;
			return this;
		}

		public RequestCreationResponseDtoBuilder setClusterId(String clusterId) {
			this.clusterId = clusterId;
			return this;
		}

		public RequestCreationResponseDtoBuilder setClusterName(String clusterName) {
			this.clusterName = clusterName;
			return this;
		}

		public RequestCreationResponseDtoBuilder setSubClusterId(String subClusterId) {
			this.subClusterId = subClusterId;
			return this;
		}

		public RequestCreationResponseDtoBuilder setSubClusterName(String subClusterName) {
			this.subClusterName = subClusterName;
			return this;
		}

		public RequestCreationResponseDtoBuilder setGradeId(String gradeId) {
			this.gradeId = gradeId;
			return this;
		}

		public RequestCreationResponseDtoBuilder setGrade(String grade) {
			this.grade = grade;
			return this;
		}

		public RequestCreationResponseDtoBuilder setTechStack(String techStack) {
			this.techStack = techStack;
			return this;
		}

		public RequestCreationResponseDtoBuilder setCoreSkill(String coreSkill) {
			this.coreSkill = coreSkill;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setProjectCode(String projectCode) {
			this.projectCode = projectCode;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setLanded(String landed) {
			this.landed = landed;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setReasonForDemand(String reasonForDemand) {
			this.reasonForDemand = reasonForDemand;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setIfReplacement(String ifReplacement) {
			this.ifReplacement = ifReplacement;
			return this;
		}

		public RequestCreationResponseDtoBuilder setSkillDetails(String skillDetails) {
			this.skillDetails = skillDetails;
			return this;
		}

		public RequestCreationResponseDtoBuilder setStartDate(String startDate) {
			this.startDate = startDate;
			return this;
		}

		public RequestCreationResponseDtoBuilder setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public RequestCreationResponseDtoBuilder setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public RequestCreationResponseDtoBuilder setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public RequestCreationResponseDtoBuilder setOwnerId(String ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setOwnerName(String ownerName) {
			this.ownerName = ownerName;
			return this;
		}

		public RequestCreationResponseDtoBuilder setWorkLocation(String workLocation) {
			this.workLocation = workLocation;
			return this;
		}

		public RequestCreationResponseDtoBuilder setArea(String area) {
			this.area = area;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setServiceLine(String serviceLine) {
			this.serviceLine = serviceLine;
			return this;
		}
		
		public RequestCreationResponseDtoBuilder setBucketSkills(String bucketSkills) {
			this.bucketSkills = bucketSkills;
			return this;
		}

		public RequestCreationResponseDtoBuilder setSoNumber(String soNumber) {
			this.soNumber = soNumber;
			return this;
		}

		public RequestCreationResponseDtoBuilder setStatus(String status) {
			this.status = status;
			return this;
		}

		public RequestCreationResponseDtoBuilder setComment(String comment) {
			this.comment = comment;
			return this;
		}
		
//		public RequestCreationResponseDtoBuilder setTags(List<String> tags) {
//			this.tags = tags;
//			return this;
//		}

		public RequestCreationResponseDtoBuilder setToastrMessage(String toastrMessage) {
			this.toastrMessage = toastrMessage;
			return this;
		}

		public RequestCreationResponseDto build() {
			return new RequestCreationResponseDto(this);
		}
	}

}
