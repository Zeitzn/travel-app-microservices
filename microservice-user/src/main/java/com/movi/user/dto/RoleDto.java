package com.movi.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleDto extends Auditable {

	private String id;

	@Schema(description = "Role name, with prefix `ROLE_`", example = "ROLE_ADMIN", required = true)
	@NotBlank(message = "Enter name")
	@NotEmpty(message = "Enter name")
	@Size(max = 25, min = 6, message = "The name must contain {min} to {max} characters")
	private String name;
}
