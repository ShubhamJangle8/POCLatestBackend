package com.ikea.resourceallocation.requestscrud.service;

import java.util.List;

import com.ikea.resourceallocation.requestscrud.dto.ClustersDto;
import com.ikea.resourceallocation.requestscrud.model.Cluster;

public interface ClusterService {

	public List<ClustersDto> getAllClustersService(boolean clustersOnly);

}