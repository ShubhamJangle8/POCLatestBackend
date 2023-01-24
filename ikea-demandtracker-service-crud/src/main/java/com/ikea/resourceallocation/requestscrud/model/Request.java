package com.ikea.resourceallocation.requestscrud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ikea.resourceallocation.requestscrud.customidgeneration.StringPrefixedSequenceIdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ubakara Anthony F
 * @since 2021-10-24
 * @version 1.0
 */
@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
public class Request implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req_id_seq")
	@GenericGenerator(name = "req_id_seq", strategy = "com.ikea.resourceallocation.requestscrud.customidgeneration.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "REQ"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%09d") })
	@Column(name = "req_id", updatable = false)
	private String reqId;

	@Column(name = "req_cluster_id")
	private String clusterId;

	@Transient
	private String clusterName;

	@Column(name = "req_sub_cluster_id")
	private String subClusterId;

	@Transient
	private String subClusterName;

	@Column(name = "grade_id")
	private String gradeId;

	@Transient
	private String grade;

	@Column(name = "tech_stack")
	private String techStack;

	@Column(name = "core_skill")
	private String coreSkill;
	
	@Column(name = "project_code", nullable = false, insertable = true, updatable = true)
	private String projectCode;

	@Column(name = "landed", nullable = false, insertable = true, updatable = true)
	private String landed;
	
	@Column(name = "reason", nullable = false, insertable = true, updatable = true)
	private String reasonForDemand;
	
	@Column(name = "if_replacement", nullable = false, insertable = true, updatable = true)
	private String ifReplacement;

	@Column(name = "details")
	private String skillDetails;

	@Column(name = "start_date")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private String startDate;

	@Column(name = "req_created_date")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private String createdDate;

	@Column(name = "owner_id")
	private String ownerId;
	
	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "work_location", nullable = false, insertable = true, updatable = true)
	private String workLocation;

	@Column(name = "area", nullable = false, insertable = true, updatable = true)
	private String area;
	
	@Column(name = "service_line", nullable = false, insertable = true, updatable = true)
	private String serviceLine;
	
	@Column(name = "bucket_skills", nullable = false, insertable = true, updatable = true)
	private String bucketSkills;

	@Column(name = "so_number", nullable = true, insertable = true, updatable = true)
	private String soNumber;

	@Column(name = "req_status", nullable = true, insertable = true, updatable = true)
	private String status;

	@Column(name = "comment", nullable = true, insertable = true, updatable = true)
	private String comment;

	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
//	@Column(name = "tags", nullable = true, insertable = true, updatable = true)
//	private List<String> tags;

	@Column(name = "updated_at", nullable = false, insertable = true, updatable = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	public void onSave() {
		Date currentDate = new Date();
		this.createdAt = currentDate;
		this.updatedAt = currentDate;
	}
	
	@PreUpdate
	public void onUpdate() {
		Date currentDate = new Date();
		this.updatedAt = currentDate;
	}

}
