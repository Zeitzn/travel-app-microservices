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

import com.movi.user.dto.BusinessVehicleDto;
import com.movi.user.entity.BusinessVehicle;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IBusinessVehicleService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/business-vehicle")
@Tag(name = "BusinessVehicle", description = "the BusinessVehicle API")
public class BusinessVehicleController {

	private static final Logger log = LoggerFactory.getLogger(BusinessVehicleController.class);

	@Autowired
	private IBusinessVehicleService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<BusinessVehicle, BusinessVehicleDto> mapperUtil;

	@Operation(summary = "Add a new BusinessVehicle", description = "", tags = { "BusinessVehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "BusinessVehicle created",
                content = @Content(schema = @Schema(implementation = BusinessVehicleDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "BusinessVehicle already exists") })
	@PostMapping(value = { "/register-vehicle" },consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessVehicleDto> registerWithVehicle(@Valid @RequestBody BusinessVehicleDto businessVehicleDto) {
		BusinessVehicle result = service.registerWithVehicle(businessVehicleDto);
		return new ResponseEntity<>(mapper.map(result, BusinessVehicleDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing BusinessVehicle", description = "", tags = { "BusinessVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "BusinessVehicle not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessVehicleDto> update(@Valid @RequestBody BusinessVehicleDto businessVehicleDto) {
		if (businessVehicleDto.getId() == null) {
			throw new ResourceNotFoundException("BusinessVehicle","id","0");
		}
		BusinessVehicle result = service.update(mapper.map(businessVehicleDto, BusinessVehicle.class));
		return new ResponseEntity<>(mapper.map(result, BusinessVehicleDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find BusinessVehicle by ID", description = "Returns a single BusinessVehicle", tags = { "BusinessVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = BusinessVehicleDto.class))),
        @ApiResponse(responseCode = "404", description = "BusinessVehicle not found") })
	@GetMapping("/{id}")
	public ResponseEntity<BusinessVehicleDto> findById(@PathVariable("id") String id) {
		BusinessVehicle businessVehicleDto = service.findById(id);
		BusinessVehicleDto result = null;
		if (businessVehicleDto != null) {
			result = mapper.map(businessVehicleDto, BusinessVehicleDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all BusinessVehicles", description = "", tags = { "BusinessVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<BusinessVehicleDto>> findAll() {
		List<BusinessVehicle> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a BusinessVehicle", description = "", tags = { "BusinessVehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "BusinessVehicle not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<BusinessVehicleDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		BusinessVehicle businessVehicleDto = service.findById(id);
		if (businessVehicleDto == null) {
			throw new ResourceNotFoundException("BusinessVehicle", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
