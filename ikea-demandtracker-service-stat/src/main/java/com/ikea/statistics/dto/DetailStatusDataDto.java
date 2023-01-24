package com.ikea.statistics.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailStatusDataDto {

	private String label;
	private String x;
	private String y;
}
