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

import com.movi.user.dto.BusinessDto;
import com.movi.user.entity.Business;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IBusinessService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/business")
@Tag(name = "Business", description = "the Business API")
public class BusinessController {

	private static final Logger log = LoggerFactory.getLogger(BusinessController.class);

	@Autowired
	private IBusinessService service;	

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<Business, BusinessDto> mapperUtil;

	@Operation(summary = "Add a new Business", description = "", tags = { "Business" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Business created",
                content = @Content(schema = @Schema(implementation = BusinessDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Business already exists") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDto> register(@Valid @RequestBody BusinessDto business) {		
		Business result = service.register(mapper.map(business, Business.class));
		return new ResponseEntity<>(mapper.map(result, BusinessDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing Business", description = "", tags = { "Business" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Business not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDto> update(@Valid @RequestBody BusinessDto business) {
		if (business.getId() == null) {
			throw new ResourceNotFoundException("Business","id","0");
		}
		Business result = service.update(mapper.map(business, Business.class));
		return new ResponseEntity<>(mapper.map(result, BusinessDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find Business by ID", description = "Returns a single Business", tags = { "Business" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = BusinessDto.class))),
        @ApiResponse(responseCode = "404", description = "Business not found") })
	@GetMapping("/{id}")
	public ResponseEntity<BusinessDto> findById(@PathVariable("id") String id) {
		Business business = service.findById(id);
		BusinessDto result = null;
		if (business != null) {
			result = mapper.map(business, BusinessDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all Businesss", description = "", tags = { "Business" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<BusinessDto>> findAll() {
		List<Business> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a Business", description = "", tags = { "Business" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "Business not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<BusinessDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		Business business = service.findById(id);
		if (business == null) {
			throw new ResourceNotFoundException("Business", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
