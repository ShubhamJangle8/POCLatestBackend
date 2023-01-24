package com.ikea.statistics.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ikea.statistics.constants.ColorsEnum;
import com.ikea.statistics.constants.StatusEnum;
import com.ikea.statistics.dao.StatisticsDAO;
import com.ikea.statistics.dto.ChartData;
import com.ikea.statistics.dto.ChartDataSet;
import com.ikea.statistics.dto.DetailStatusDataDto;
import com.ikea.statistics.dto.DetailStatusDto;
import com.ikea.statistics.dto.DetailStatusResponse;
import com.ikea.statistics.dto.StatusCardsDto;
import com.ikea.statistics.dto.SubClustersDto;
import com.ikea.statistics.dto.UserRole;

@Service
@Transactional
public class StatisticsService {

	@Autowired
	private StatisticsDAO statisticsDAO;

	public StatusCardsDto getRequestsCountByStatusService() {
		StatusCardsDto statusCards = new StatusCardsDto();
		Map<String, String> statusWithCounts = getCount(StatusEnum.values());
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM");
		statusCards.setMonth("In " + formatter.format(currentDate));
		statusCards.setStatus(statusWithCounts);
		return statusCards;
	}

	public ChartDataSet chartData(String clusId) {
		int getCurrentYear = 2023;
		Month[] months = Month.values();
		List<Integer> data = null;
		List<ChartData> chartData = new ArrayList<>();
		List<SubClustersDto> subClusters = statisticsDAO.getSubClustersByClusterId(clusId);
		ColorsEnum[] colors = ColorsEnum.values();
		int i = 0;
		for (SubClustersDto sub : subClusters) {
			data = new ArrayList<>();
			for (Month month : months) {
				YearMonth yearMonth = YearMonth.of(getCurrentYear, month.getValue());
				LocalDate firstDay = yearMonth.atDay(1);
				LocalDate lastDay = yearMonth.atEndOfMonth();
				Integer count = statisticsDAO.getRequestsCountBySubClusAndDates(sub.getSubClusId(), firstDay, lastDay);
				data.add(count);
			}
			String color = colors[i].hexCode;
			chartData.add(ChartData.builder().labelId(sub.getSubClusId()).label(sub.getSubCluster()).data(data)
					.backgroundColor(color).build());
			i++;
		}
		ChartDataSet charDataSet = new ChartDataSet();
		charDataSet.setDatasets(chartData);
		return charDataSet;
	}

	private Map<String, String> getCount(StatusEnum[] status) {
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();
		YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
		LocalDate firstDay = yearMonth.atDay(1);
		LocalDate lastDay = yearMonth.atEndOfMonth();
		Map<String, String> statusWithCount = new HashMap<>();
		for (StatusEnum s : status) {
			if (!s.toString().toLowerCase().equals(StatusEnum.WITHDRAW.toString().toLowerCase())
					|| !UserRole.getInstance().getReqOrPmo().equals("PMO")) {
				Integer count = statisticsDAO.getRequestsCountByStatus(s.toString().toLowerCase(), firstDay, lastDay);
				statusWithCount.put(s.toString().toLowerCase(), count.toString());
			}

		}
		return statusWithCount;

	}

	public DetailStatusResponse getDetailStatusBySubCluster(String color, String subClusterId, String subclusterName,
			int month, int year) {
		YearMonth yearMonth = YearMonth.of(year, month);
		StatusEnum[] allStatus = StatusEnum.values();
		List<DetailStatusDataDto> dataList = getCount(subClusterId, allStatus, yearMonth);

		if (month != LocalDate.now().getMonthValue()) {
			DetailStatusDataDto toBeDeleted = dataList.stream().filter(
					data -> data.getLabel().toLowerCase().equals(StatusEnum.INPROGRESS.toString().toLowerCase()))
					.findFirst().get();
			dataList.remove(toBeDeleted);
		}
		if (UserRole.getInstance().getReqOrPmo().equals("PMO")) {
			DetailStatusDataDto toBeDeleted = dataList.stream()
					.filter(data -> data.getLabel().toLowerCase().equals(StatusEnum.WITHDRAW.toString().toLowerCase()))
					.findFirst().get();
			dataList.remove(toBeDeleted);
		}

		DetailStatusDto detailStatusDto = DetailStatusDto
				.builder().label(subclusterName + " ("
						+ StringUtils.capitalize(yearMonth.getMonth().toString().toLowerCase()) + ")")
				.data(dataList).borderColor(color).backgroundColor(color).build();

		List<DetailStatusDto> detailStatusLi = new ArrayList<>();
		detailStatusLi.add(detailStatusDto);
		DetailStatusResponse detailStatusResponse = new DetailStatusResponse();
		detailStatusResponse.setDatasets(detailStatusLi);
		return detailStatusResponse;
	}

	private List<DetailStatusDataDto> getCount(String subCluster, StatusEnum[] status, YearMonth yearAndMonth) {
		LocalDate firstDay = yearAndMonth.atDay(1);
		LocalDate lastDay = yearAndMonth.atEndOfMonth();
		System.out.println(firstDay + " : " + lastDay);
		List<DetailStatusDataDto> dataLi = new ArrayList<>();
		DetailStatusDataDto detailStatusDataDto = null;
		for (StatusEnum s : status) {
			Integer count = statisticsDAO.getRequestCountBySubClusAndStatusAndDates(subCluster,
					s.toString().toLowerCase(), firstDay, lastDay);

			detailStatusDataDto = DetailStatusDataDto.builder()
					.label(StringUtils.capitalize(s.toString().toLowerCase()))
					.x(StringUtils.capitalize(String.valueOf(s).toLowerCase())).y(count.toString()).build();
			dataLi.add(detailStatusDataDto);

		}
		return dataLi;

	}
}
