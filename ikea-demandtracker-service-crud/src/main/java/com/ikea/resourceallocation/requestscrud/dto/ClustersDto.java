package com.ikea.resourceallocation.requestscrud.dto;

import java.util.List;

import com.ikea.resourceallocation.requestscrud.model.SubClusters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClustersDto {

	private String clusterId;

	private String cluster;
	
	private List<SubClusters> subClusters;
}
