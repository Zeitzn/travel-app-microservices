package com.movi.user.dto;

import javax.validation.constraints.NotNull;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusinessDriverDto extends Auditable {

	private String id;
	
	@Schema(description = "Business", example = "", required = true)
	@NotNull(message = "Enter business")
	private BusinessDto business;
	
	@Schema(description = "Driver", example = "", required = true)
	@NotNull(message = "Enter driver user")
	private SignUpDto driver;
	
}