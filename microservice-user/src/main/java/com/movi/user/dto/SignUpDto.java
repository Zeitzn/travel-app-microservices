package com.movi.user.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.movi.user.config.Auditable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpDto extends Auditable {
	
	private String id;
	
	private String imageUrl;
	
	@Schema(description = "User first name", example = "Isac", required = true)
	@NotBlank(message = "Enter firstName")
	@NotEmpty(message = "Enter firstName")
	@Size(min = 2, max = 25, message = "The firstName must contain {min} to {max} characters")
	private String firstName;

	@Schema(description = "User last name", example = "Huamán Pineda", required = true)
	@NotBlank(message = "Enter lastName")
	@NotEmpty(message = "Enter lastName")
	@Size(min = 2, max = 50, message = "The lastName must contain {min} to {max} characters")
	private String lastName;

	@Schema(description = "Username", example = "user32", required = true)
	@NotBlank(message = "Enter username")
	@NotEmpty(message = "Enter username")
	@Size(min = 2, max = 25, message = "The username must contain {min} to {max} characters")
	private String username;

	@Schema(description = "User email", example = "isac@gmail.com", required = true)
	@NotBlank(message = "Enter email")
	@NotEmpty(message = "Enter email")
	@Email(message = "Email Address")
	@Size(min = 2, max = 50, message = "The email must contain {min} to {max} characters")
	private String email;

	@Schema(description = "User password", example = "", required = true)
	@NotBlank(message = "Enter password")
	@NotEmpty(message = "Enter password")
	private String password;

//	@Schema(description = "User type", example = "1", required = true)
//	@NotNull(message = "Enter user type")
//	private UserTypeDto userType;

	@Schema(description = "User roles", example = "[{'name':'ROLE_ADMIN'}]", required = true)
	@NotNull(message = "Enter user roles list")
	private Set<RoleDto> roles;
	
	private boolean available;
	private boolean active;
	private boolean enable;
}
