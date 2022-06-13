package com.movi.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusinessDto extends Auditable {

	private String id;

	@Schema(description = "Business name", required = true)
	@NotBlank(message = "Enter name")
	@NotEmpty(message = "Enter name")
	@Size(max = 200, min = 2, message = "The name must contain {min} to {max} characters")
	private String name;
	
	@Schema(description = "Business name", required = false)
	private String code;
	
	@Schema(description = "Business email", example = "business@gmail.com", required = true)
	@NotBlank(message = "Enter email")
	@NotEmpty(message = "Enter email")
	@Email(message = "Email Address")
	@Size(min = 2, max = 50, message = "The email must contain {min} to {max} characters")
	private String email;
	
	@Schema(description = "Drivers number", example = "10", required = true)
	@NotNull(message = "Enter driversNumber")
	private Integer driversNumber;
	
//	@Schema(description = "Business first Staff", example = "10", required = true)
//	@NotNull(message = "Enter staff")
//	private StaffDto staff;
}
