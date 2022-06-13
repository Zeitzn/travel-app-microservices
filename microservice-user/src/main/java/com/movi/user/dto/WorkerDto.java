package com.movi.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WorkerDto extends Auditable {

	private String id;

	@Schema(description = "Staff firstName", required = true)
	@NotBlank(message = "Enter firstName")
	@NotEmpty(message = "Enter firstName")
	@Size(max = 200, min = 2, message = "The firstName must contain {min} to {max} characters")
	private String firstName;
	
	@Schema(description = "Staff lastName", required = true)
	@NotBlank(message = "Enter lastName")
	@NotEmpty(message = "Enter lastName")
	@Size(max = 200, min = 2, message = "The lastName must contain {min} to {max} characters")
	private String lastName;
	
	@Schema(description = "Staff user account", example = "", required = true)
	@NotNull(message = "Enter user")
	private SignUpDto user;
	
	@Schema(description = "Staff business", example = "", required = true)
	@NotNull(message = "Enter business")
	private BusinessDto business;
	
}
