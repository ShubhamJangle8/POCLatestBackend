package com.ikea.email.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailRequest {
	private String message;
	private String subject;
	private String to;
	private String from;
}
