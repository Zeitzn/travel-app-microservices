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

import com.movi.user.dto.BusinessDriverVehicleDto;
import com.movi.user.entity.BusinessDriverVehicle;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IBusinessDriverVehicleService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/business-driver-vehicle")
@Tag(name = "BusinessDriverVehicle", description = "the BusinessDriverVehicle API")
public class BusinessDriverVehicleController {

	private static final Logger log = LoggerFactory.getLogger(BusinessDriverVehicleController.class);

	@Autowired
	private IBusinessDriverVehicleService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<BusinessDriverVehicle, BusinessDriverVehicleDto> mapperUtil;

	@Operation(summary = "Add a new BusinessDriverVehicle", description = "", tags = { "BusinessDriverVehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "BusinessDriverVehicle created",
                content = @Content(schema = @Schema(implementation = BusinessDriverVehicleDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "BusinessDriverVehicle already exists") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDriverVehicleDto> register(@Valid @RequestBody BusinessDriverVehicleDto businessDriverVehicleDto) {
		BusinessDriverVehicle result = service.register(mapper.map(businessDriverVehicleDto, BusinessDriverVehicle.class));
		return new ResponseEntity<>(mapper.map(result, BusinessDriverVehicleDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing BusinessDriverVehicle", description = "", tags = { "BusinessDriverVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "BusinessDriverVehicle not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDriverVehicleDto> update(@Valid @RequestBody BusinessDriverVehicleDto businessDriverVehicleDto) {
		if (businessDriverVehicleDto.getId() == null) {
			throw new ResourceNotFoundException("BusinessDriverVehicle","id","0");
		}
		BusinessDriverVehicle result = service.update(mapper.map(businessDriverVehicleDto, BusinessDriverVehicle.class));
		return new ResponseEntity<>(mapper.map(result, BusinessDriverVehicleDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find BusinessDriverVehicle by ID", description = "Returns a single BusinessDriverVehicle", tags = { "BusinessDriverVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = BusinessDriverVehicleDto.class))),
        @ApiResponse(responseCode = "404", description = "BusinessDriverVehicle not found") })
	@GetMapping("/{id}")
	public ResponseEntity<BusinessDriverVehicleDto> findById(@PathVariable("id") String id) {
		BusinessDriverVehicle businessDriverVehicleDto = service.findById(id);
		BusinessDriverVehicleDto result = null;
		if (businessDriverVehicleDto != null) {
			result = mapper.map(businessDriverVehicleDto, BusinessDriverVehicleDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all BusinessDriverVehicles", description = "", tags = { "BusinessDriverVehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<BusinessDriverVehicleDto>> findAll() {
		List<BusinessDriverVehicle> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a BusinessDriverVehicle", description = "", tags = { "BusinessDriverVehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "BusinessDriverVehicle not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<BusinessDriverVehicleDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		BusinessDriverVehicle businessDriverVehicleDto = service.findById(id);
		if (businessDriverVehicleDto == null) {
			throw new ResourceNotFoundException("BusinessDriverVehicle", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
