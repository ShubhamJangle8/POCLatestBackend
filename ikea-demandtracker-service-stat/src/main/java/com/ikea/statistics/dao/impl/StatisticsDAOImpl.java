package com.ikea.statistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.ikea.statistics.dao.StatisticsDAO;
import com.ikea.statistics.dto.SubClustersDto;
import com.ikea.statistics.dto.UserRole;

@Repository
public class StatisticsDAOImpl implements StatisticsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getRequestsCountByStatus(String status, LocalDate from, LocalDate to) {
		String sqlForFetchCount = "SELECT count(req_id) FROM requests where req_status=? and updated_at between ? and ? ";

		System.out.println("time" + to.atTime(23, 59));
		SqlRowSet count = jdbcTemplate.queryForRowSet(sqlForFetchCount,
				new Object[] { status, from, to.atTime(23, 59) });
		if (count.next()) {
			return count.getInt(1);
		}
		return 0;
	}

	@Override
	public List<SubClustersDto> getSubClustersByClusterId(String clusId) {
		String sqlForFetchSubClusters = "SELECT sub_clus_id, sub_cluster FROM sub_cluster where clus_id='" + clusId
				+ "'";
		List<SubClustersDto> subClusters = jdbcTemplate.query(sqlForFetchSubClusters, new RowMapper<SubClustersDto>() {

			@Override
			public SubClustersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubClustersDto subCluster = new SubClustersDto();
				subCluster.setSubClusId(rs.getString("sub_clus_id"));
				subCluster.setSubCluster(rs.getString("sub_cluster"));
				return subCluster;
			}

		});
		return subClusters;
	}

	@Override
	public Integer getRequestsCountBySubClusAndDates(String subCluster, LocalDate from, LocalDate to) {
		String sqlForFetchCount = null;
		if (UserRole.getInstance().getReqOrPmo().equals("PMO")) {
			sqlForFetchCount = "SELECT count(req_id) FROM requests where req_sub_cluster_id=? and updated_at between ? and ? and not req_status='withdraw'";
		}

		if (UserRole.getInstance().getReqOrPmo().equals("REQ")) {
			sqlForFetchCount = "SELECT count(req_id) FROM requests where req_sub_cluster_id=? and updated_at between ? and ?";
		}

		SqlRowSet count = jdbcTemplate.queryForRowSet(sqlForFetchCount,
				new Object[] { subCluster, from, to.atTime(23, 59) });
		if (count.next()) {
			return count.getInt(1);
		}
		return 0;
	}

	@Override
	public Integer getRequestCountBySubClusAndStatusAndDates(String subCluster, String status, LocalDate from,
			LocalDate to) {
		String sqlForFetchCount = "SELECT count(req_id) FROM requests where req_sub_cluster_id=? and req_status=? and updated_at between ? and ?";

		SqlRowSet count = jdbcTemplate.queryForRowSet(sqlForFetchCount,
				new Object[] { subCluster, status, from, to.atTime(23, 59) });
		if (count.next()) {
			return count.getInt(1);
		}
		return 0;
	}

}
