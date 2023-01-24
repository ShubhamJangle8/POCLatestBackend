package com.ikea.resourceallocation.requestscrud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cluster")
public class Cluster {

	@Id
	@Column(name = "clus_id")
	private String clusterId;

	@Column(name = "cluster")
	private String cluster;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cluster", targetEntity = SubClusters.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SubClusters> subClusters;
}
