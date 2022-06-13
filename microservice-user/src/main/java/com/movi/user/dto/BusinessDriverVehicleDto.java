package com.movi.user.dto;

import javax.validation.constraints.NotNull;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusinessDriverVehicleDto extends Auditable {

	private String id;
	
	@Schema(description = "Business driver", example = "", required = true)
	@NotNull(message = "Enter businessDriver")
	private BusinessDriverDto businessDriver;
	
	@Schema(description = "Business vehicle", example = "", required = true)
	@NotNull(message = "Enter businessVehicle")
	private BusinessVehicleDto businessVehicle;
	
}