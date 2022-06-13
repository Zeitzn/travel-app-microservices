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

import com.movi.user.dto.BusinessDriverDto;
import com.movi.user.entity.BusinessDriver;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IBusinessDriverService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/business-driver")
@Tag(name = "BusinessDriver", description = "the BusinessDriver API")
public class BusinessDriverController {

	private static final Logger log = LoggerFactory.getLogger(BusinessDriverController.class);

	@Autowired
	private IBusinessDriverService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<BusinessDriver, BusinessDriverDto> mapperUtil;

	@Operation(summary = "Add a new BusinessDriver", description = "", tags = { "BusinessDriver" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "BusinessDriver created",
                content = @Content(schema = @Schema(implementation = BusinessDriverDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "BusinessDriver already exists") })
	@PostMapping(value = { "/signup" },consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDriverDto> signUp(@Valid @RequestBody BusinessDriverDto businessDriverDto) {
		BusinessDriver result = service.signUp(businessDriverDto);
		return new ResponseEntity<>(mapper.map(result, BusinessDriverDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing BusinessDriver", description = "", tags = { "BusinessDriver" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "BusinessDriver not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDriverDto> update(@Valid @RequestBody BusinessDriverDto businessDriverDto) {
		if (businessDriverDto.getId() == null) {
			throw new ResourceNotFoundException("BusinessDriver","id","0");
		}
		BusinessDriver result = service.update(mapper.map(businessDriverDto, BusinessDriver.class));
		return new ResponseEntity<>(mapper.map(result, BusinessDriverDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find BusinessDriver by ID", description = "Returns a single BusinessDriver", tags = { "BusinessDriver" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = BusinessDriverDto.class))),
        @ApiResponse(responseCode = "404", description = "BusinessDriver not found") })
	@GetMapping("/{id}")
	public ResponseEntity<BusinessDriverDto> findById(@PathVariable("id") String id) {
		BusinessDriver businessDriverDto = service.findById(id);
		BusinessDriverDto result = null;
		if (businessDriverDto != null) {
			result = mapper.map(businessDriverDto, BusinessDriverDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all BusinessDrivers", description = "", tags = { "BusinessDriver" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<BusinessDriverDto>> findAll() {
		List<BusinessDriver> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a BusinessDriver", description = "", tags = { "BusinessDriver" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "BusinessDriver not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<BusinessDriverDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		BusinessDriver businessDriverDto = service.findById(id);
		if (businessDriverDto == null) {
			throw new ResourceNotFoundException("BusinessDriver", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
