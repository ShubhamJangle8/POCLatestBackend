package com.ikea.statistics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailStatusRequestDto {

	private String color;
	private String subClusterId;
	private String subClusterName;
	private int month;
	private int year;
}
