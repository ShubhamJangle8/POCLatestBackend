package com.ikea.resourceallocation.requestscrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikea.resourceallocation.requestscrud.model.SubClusters;

@Repository
public interface SubClusterRepo extends JpaRepository<SubClusters, String> {
	public SubClusters findSubClusterBySubClusId(String subClusId);

}
