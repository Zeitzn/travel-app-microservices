package com.movi.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserTypeDto extends Auditable {
	
	private String id;
	
	@Schema(description = "User type name, separate multiple types with  `/`", example = "Driver, Driver/Passenger", required = true)
	@NotBlank(message = "Enter name")
	@NotEmpty(message = "Enter name")
	@Size(max = 50, min = 4, message = "The name must contain {min} to {max} characters")
	private String name;
}
