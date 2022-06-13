package com.movi.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VehicleDto extends Auditable {

	private String id;

	@Schema(description = "Vehicle number plate", example = "PEO2022", required = true)
	@NotBlank(message = "Enter numberPlate")
	@NotEmpty(message = "Enter numberPlate")
	@Size(max = 25, min = 6, message = "The numberPlate must contain {min} to {max} characters")
	private String numberPlate;
	
	@Schema(description = "Vehicle model", example = "2022", required = true)
	@NotBlank(message = "Enter model")
	@NotEmpty(message = "Enter model")
	private Integer model;
}
