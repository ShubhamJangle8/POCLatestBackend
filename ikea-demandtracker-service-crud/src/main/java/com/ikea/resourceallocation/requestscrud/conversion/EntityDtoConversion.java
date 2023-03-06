package com.ikea.resourceallocation.requestscrud.conversion;

import java.util.Arrays;
import java.util.List;

import com.ikea.resourceallocation.requestscrud.dto.RequestCreationResponseDto;
import com.ikea.resourceallocation.requestscrud.model.Request;

public class EntityDtoConversion {

	public RequestCreationResponseDto EntityToDtoConvertion(Request req, String message) {
		
		List<String> tags = Arrays.asList(req.getTags().split(","))
        .stream()
        .map(String::trim).toList();
        
		RequestCreationResponseDto request = 
				new RequestCreationResponseDto.RequestCreationResponseDtoBuilder()
				.setReqId(req.getReqId())
				.setClusterId(req.getClusterId())
				.setClusterName(req.getClusterName())
				.setSubClusterId(req.getSubClusterId())
				.setSubClusterName(req.getSubClusterName())
				.setGradeId(req.getGradeId())
				.setGrade(req.getGrade())
				.setTechStack(req.getTechStack())
				.setCoreSkill(req.getCoreSkill())
				.setProjectCode(req.getProjectCode())
				.setLanded(req.getLanded())
				.setReasonForDemand(req.getReasonForDemand())
				.setIfReplacement(req.getIfReplacement())
				.setSkillDetails(req.getSkillDetails())
				.setStartDate(req.getStartDate())
				.setCreatedDate(req.getCreatedDate())
				.setOwnerId(req.getOwnerId())
				.setOwnerName(req.getOwnerName())
				.setWorkLocation(req.getWorkLocation())
				.setArea(req.getArea())
				.setServiceLine(req.getServiceLine())
				.setBucketSkills(req.getBucketSkills())
				.setSoNumber(req.getSoNumber())
				.setStatus(req.getStatus())
				.setComment(req.getComment())
				.setTags(tags)
				.setToastrMessage(message)
				.setCreatedAt(req.getCreatedAt())
				.setUpdatedAt(req.getUpdatedAt())
				.build();
		return request;
	}
	
}
