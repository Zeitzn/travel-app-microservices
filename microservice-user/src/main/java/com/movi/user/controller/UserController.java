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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movi.user.dto.UserDto;
import com.movi.user.entity.User;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IUserService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "user", description = "the User API")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<User, UserDto> mapperUtil;

	@Operation(summary = "Update an existing user", description = "", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userTypeDto) {
		if (userTypeDto.getId() == null) {
			throw new ResourceNotFoundException("User","id","0");
		}
		User result = service.update(mapper.map(userTypeDto, User.class));
		return new ResponseEntity<>(mapper.map(result, UserDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find user by ID", description = "Returns a single user", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "404", description = "User not found") })
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable("id") String id) {
		User userType = service.findById(id);
		UserDto result = null;
		if (userType != null) {
			result = mapper.map(userType, UserDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all users", description = "", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		List<User> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a user", description = "", tags = { "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "User not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		User userType = service.findById(id);
		if (userType == null) {
			throw new ResourceNotFoundException("User", "id", id.toString());
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
