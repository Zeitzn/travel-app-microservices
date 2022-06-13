package com.movi.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movi.user.dto.UserTypeDto;
import com.movi.user.entity.UserType;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IUserTypeService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user-type")
@Tag(name = "user-type", description = "the UserType API")
public class UserTypeController {

	private static final Logger log = LoggerFactory.getLogger(UserTypeController.class);

	@Autowired
	private IUserTypeService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<UserType, UserTypeDto> mapperUtil;

	@Operation(summary = "Add a new user type", description = "", tags = { "user-type" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "UserType created",
                content = @Content(schema = @Schema(implementation = UserTypeDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "UserType already exists") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTypeDto> register(@Valid @RequestBody UserTypeDto userTypeDto) {
		log.info(userTypeDto.getName());
		UserType result = service.register(mapper.map(userTypeDto, UserType.class));
		return new ResponseEntity<>(mapper.map(result, UserTypeDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing user type", description = "", tags = { "user-type" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "UserType not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTypeDto> update(@Valid @RequestBody UserTypeDto userTypeDto) {
		if (userTypeDto.getId() == null) {
			throw new ResourceNotFoundException("UserType","id","0");
		}
		UserType result = service.update(mapper.map(userTypeDto, UserType.class));
		return new ResponseEntity<>(mapper.map(result, UserTypeDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find user type by ID", description = "Returns a single user type", tags = { "user-type" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = UserTypeDto.class))),
        @ApiResponse(responseCode = "404", description = "UserType not found") })
	@GetMapping("/{id}")
	public ResponseEntity<UserTypeDto> findById(@PathVariable("id") String id) {
		UserType userType = service.findById(id);
		UserTypeDto result = null;
		if (userType != null) {
			result = mapper.map(userType, UserTypeDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all user types", description = "", tags = { "user-type" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<UserTypeDto>> findAll() {
		List<UserType> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a user type", description = "", tags = { "user-type" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "UserType not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<UserTypeDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		UserType userType = service.findById(id);
		if (userType == null) {
			throw new ResourceNotFoundException("UserType", "id", id.toString());
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
