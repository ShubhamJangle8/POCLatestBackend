package com.ikea.statistics.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusCardsDto {

	private String month;
	private Map<String, String> status;
}
