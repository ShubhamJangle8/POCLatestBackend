package com.ikea.statistics.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChartData {

	private String labelId;
	private String label;
	private List<Integer> data;
	private String backgroundColor;
}
