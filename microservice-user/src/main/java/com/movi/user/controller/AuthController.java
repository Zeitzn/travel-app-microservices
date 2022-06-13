package com.movi.user.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movi.user.dto.SignUpDto;
import com.movi.user.dto.UserTypeDto;
import com.movi.user.entity.Role;
import com.movi.user.entity.User;
import com.movi.user.service.IRoleService;
import com.movi.user.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "the Auth API")
public class AuthController {

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService service;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;
	
	@Operation(summary = "Add a new user", description = "", tags = { "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "User created",
                content = @Content(schema = @Schema(implementation = UserTypeDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "User already exists") })
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignUpDto request) {
		if (service.existsByUsername(request.getUsername())) {
			return new ResponseEntity<>("already_username", HttpStatus.BAD_REQUEST);
		}

		if (service.existsByEmail(request.getEmail())) {
			return new ResponseEntity<>("already_email", HttpStatus.BAD_REQUEST);
		}
		User user = mapper.map(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		Set<Role> roles = user.getRoles().stream().map(x -> roleService.findByname(x.getName()))
				.collect(Collectors.toSet());
		user.setRoles(roles);

		user.setCode("PENDING");
		service.register(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}
}
