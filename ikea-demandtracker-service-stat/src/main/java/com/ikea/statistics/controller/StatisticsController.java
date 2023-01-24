package com.ikea.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikea.statistics.dto.ChartDataSet;
import com.ikea.statistics.dto.DetailStatusDto;
import com.ikea.statistics.dto.DetailStatusRequestDto;
import com.ikea.statistics.dto.DetailStatusResponse;
import com.ikea.statistics.dto.StatusCardsDto;
import com.ikea.statistics.service.StatisticsService;

@RestController
@RequestMapping("api/stats")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	@GetMapping("/req-count")
	public ResponseEntity<StatusCardsDto> getReqCountByStatus() {
		StatusCardsDto statusCards = statisticsService.getRequestsCountByStatusService();
		return new ResponseEntity<>(statusCards, HttpStatus.OK);
	}

	@GetMapping("/chart-data/{clusId}")
	public ResponseEntity<ChartDataSet> getChartData(@PathVariable String clusId) {
		ChartDataSet chartDataSet = statisticsService.chartData(clusId);
		return new ResponseEntity<>(chartDataSet, HttpStatus.OK);
	}

	@PostMapping("/detail-data")
	public ResponseEntity<DetailStatusResponse> getDetailDataBySubCluster(
			@RequestBody DetailStatusRequestDto requestDto) {
		DetailStatusResponse detailData = statisticsService.getDetailStatusBySubCluster(requestDto.getColor(),
				requestDto.getSubClusterId(), requestDto.getSubClusterName(), requestDto.getMonth(),
				requestDto.getYear());
		return new ResponseEntity<>(detailData, HttpStatus.OK);
	}
}
