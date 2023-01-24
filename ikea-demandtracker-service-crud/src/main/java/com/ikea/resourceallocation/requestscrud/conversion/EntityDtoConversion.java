package com.ikea.resourceallocation.requestscrud.conversion;

import com.ikea.resourceallocation.requestscrud.dto.RequestCreationResponseDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestsForListPageDto;
import com.ikea.resourceallocation.requestscrud.model.Request;

public class EntityDtoConversion {

	public RequestCreationResponseDto EntityToDtoConvertion(Request req, String message) {
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
//				.setTags(req.getTags())
				.setToastrMessage(message)
				.setCreatedAt(req.getCreatedAt())
				.setUpdatedAt(req.getUpdatedAt())
				.build();
		return request;
	}
	
}
