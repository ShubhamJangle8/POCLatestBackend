package com.ikea.resourceallocation.requestscrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name = "sub_cluster")
public class SubClusters {

	@Id
	@Column(name = "sub_clus_id")
	private String subClusId;

	@Column(name = "sub_cluster")
	private String subCluster;

//	@Column(name = "clus_id")
//	@JoinColumn(name = "clus_id")
//	private String clusId;

	@JoinColumn(name = "clus_id")
	@JsonBackReference
	@ManyToOne
	private Cluster cluster;
}
