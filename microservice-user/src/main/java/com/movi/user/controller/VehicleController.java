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

import com.movi.user.dto.VehicleDto;
import com.movi.user.entity.Vehicle;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IVehicleService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/vehicle")
@Tag(name = "Vehicle", description = "the Vehicle API")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private IVehicleService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<Vehicle, VehicleDto> mapperUtil;

	@Operation(summary = "Add a new Vehicle", description = "", tags = { "Vehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Vehicle created",
                content = @Content(schema = @Schema(implementation = VehicleDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Vehicle already exists") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleDto> register(@Valid @RequestBody VehicleDto Vehicle) {
		Vehicle result = service.register(mapper.map(Vehicle, Vehicle.class));
		return new ResponseEntity<>(mapper.map(result, VehicleDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing Vehicle", description = "", tags = { "Vehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleDto> update(@Valid @RequestBody VehicleDto Vehicle) {
		if (Vehicle.getId() == null) {
			throw new ResourceNotFoundException("Vehicle","id","0");
		}
		Vehicle result = service.update(mapper.map(Vehicle, Vehicle.class));
		return new ResponseEntity<>(mapper.map(result, VehicleDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find Vehicle by ID", description = "Returns a single Vehicle", tags = { "Vehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = VehicleDto.class))),
        @ApiResponse(responseCode = "404", description = "Vehicle not found") })
	@GetMapping("/{id}")
	public ResponseEntity<VehicleDto> findById(@PathVariable("id") String id) {
		Vehicle Vehicle = service.findById(id);
		VehicleDto result = null;
		if (Vehicle != null) {
			result = mapper.map(Vehicle, VehicleDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all Vehicles", description = "", tags = { "Vehicle" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<VehicleDto>> findAll() {
		List<Vehicle> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a Vehicle", description = "", tags = { "Vehicle" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<VehicleDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		Vehicle Vehicle = service.findById(id);
		if (Vehicle == null) {
			throw new ResourceNotFoundException("Vehicle", "id", id.toString());
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
