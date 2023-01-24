package com.ikea.resourceallocation.requestscrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikea.resourceallocation.requestscrud.model.Cluster;

@Repository
public interface ClusterRepo extends JpaRepository<Cluster, String> {

	public Cluster findClusterByClusterId(String clusterId);
	
}
