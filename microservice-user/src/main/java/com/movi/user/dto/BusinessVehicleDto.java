package com.movi.user.dto;

import javax.validation.constraints.NotNull;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusinessVehicleDto extends Auditable {

	private String id;
	
	@Schema(description = "Business", example = "", required = true)
	@NotNull(message = "Enter business")
	private BusinessDto business;
	
	@Schema(description = "Vehicle", example = "", required = true)
	@NotNull(message = "Enter vehicle")
	private VehicleDto vehicle;
	
}