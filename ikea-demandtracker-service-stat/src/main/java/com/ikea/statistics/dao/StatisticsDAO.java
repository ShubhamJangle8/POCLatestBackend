package com.ikea.statistics.dao;

import java.time.LocalDate;
import java.util.List;

import com.ikea.statistics.dto.SubClustersDto;

public interface StatisticsDAO {

	public Integer getRequestsCountByStatus(String status, LocalDate from, LocalDate to);

	public Integer getRequestsCountBySubClusAndDates(String subCluster, LocalDate from, LocalDate to);

	public Integer getRequestCountBySubClusAndStatusAndDates(String subCluster, String status, LocalDate from,
			LocalDate to);

	public List<SubClustersDto> getSubClustersByClusterId(String clusId);
}
