package com.ikea.resourceallocation.requestscrud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ikea.resourceallocation.requestscrud.dto.ClustersDto;
import com.ikea.resourceallocation.requestscrud.model.Cluster;
import com.ikea.resourceallocation.requestscrud.repo.ClusterRepo;
import com.ikea.resourceallocation.requestscrud.service.ClusterService;

public class ClusterServiceImpl implements ClusterService {

	@Autowired
	private ClusterRepo clusterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ClustersDto> getAllClustersService(boolean clustersOnly) {
		List<Cluster> clusters = clusterRepo.findAll();
		List<ClustersDto> cl = null;

		if (!clustersOnly) {
			cl = clusters
					.stream().map(clus -> ClustersDto.builder().clusterId(clus.getClusterId())
							.cluster(clus.getCluster()).subClusters(clus.getSubClusters()).build())
					.collect(Collectors.toList());
		} else {

			cl = clusters.stream().map(
					clus -> ClustersDto.builder().clusterId(clus.getClusterId()).cluster(clus.getCluster()).build())
					.collect(Collectors.toList());
		}
		return cl;
	}

}
