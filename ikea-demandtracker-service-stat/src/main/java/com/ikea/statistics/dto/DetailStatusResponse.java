package com.ikea.statistics.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailStatusResponse {
	private List<DetailStatusDto> datasets;
}
