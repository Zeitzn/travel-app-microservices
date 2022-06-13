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

import com.movi.user.dto.RoleDto;
import com.movi.user.entity.Role;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IRoleService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/role")
@Tag(name = "role", description = "the Role API")
public class RoleController {

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<Role, RoleDto> mapperUtil;

	@Operation(summary = "Add a new role", description = "", tags = { "role" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Role created",
                content = @Content(schema = @Schema(implementation = RoleDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Role already exists") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleDto> register(@Valid @RequestBody RoleDto role) {
		log.info(role.getName());
		Role result = service.register(mapper.map(role, Role.class));
		return new ResponseEntity<>(mapper.map(result, RoleDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing role", description = "", tags = { "role" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Role not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleDto role) {
		if (role.getId() == null) {
			throw new ResourceNotFoundException("Role","id","0");
		}
		Role result = service.update(mapper.map(role, Role.class));
		return new ResponseEntity<>(mapper.map(result, RoleDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find role by ID", description = "Returns a single role", tags = { "role" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = RoleDto.class))),
        @ApiResponse(responseCode = "404", description = "Role not found") })
	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> findById(@PathVariable("id") String id) {
		Role role = service.findById(id);
		RoleDto result = null;
		if (role != null) {
			result = mapper.map(role, RoleDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all roles", description = "", tags = { "role" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<RoleDto>> findAll() {
		List<Role> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a role", description = "", tags = { "role" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "Role not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<RoleDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		Role role = service.findById(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role", "id", id.toString());
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
