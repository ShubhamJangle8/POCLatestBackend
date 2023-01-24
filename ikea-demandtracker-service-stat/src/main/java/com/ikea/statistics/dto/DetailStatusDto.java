package com.ikea.statistics.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailStatusDto {

	private String label;
	private List<DetailStatusDataDto> data;
	private String borderColor;
	private String backgroundColor;
}
