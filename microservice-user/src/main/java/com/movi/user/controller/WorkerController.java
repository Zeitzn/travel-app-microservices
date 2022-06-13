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

import com.movi.user.dto.WorkerDto;
import com.movi.user.entity.Worker;
import com.movi.user.exceptions.ResourceNotFoundException;
import com.movi.user.service.IWorkerService;
import com.movi.user.utils.MapperUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/worker")
@Tag(name = "Worker", description = "the Worker API")
public class WorkerController {

	private static final Logger log = LoggerFactory.getLogger(WorkerController.class);

	@Autowired
	private IWorkerService service;	

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MapperUtil<Worker, WorkerDto> mapperUtil;

	@Operation(summary = "Add a new Worker", description = "", tags = { "Worker" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Worker created",
                content = @Content(schema = @Schema(implementation = WorkerDto.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Worker already exists") })
	@PostMapping(value = { "/signup" },consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkerDto> signUp(@Valid @RequestBody WorkerDto workerDto) {		
		Worker result = service.signUp(workerDto);
		return new ResponseEntity<>(mapper.map(result, WorkerDto.class), HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing Worker", description = "", tags = { "Worker" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Worker not found"),
        @ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkerDto> update(@Valid @RequestBody WorkerDto workerDto) {
		if (workerDto.getId() == null) {
			throw new ResourceNotFoundException("Worker","id","0");
		}
		Worker result = service.update(mapper.map(workerDto, Worker.class));
		return new ResponseEntity<>(mapper.map(result, WorkerDto.class), HttpStatus.OK);
	}

	@Operation(summary = "Find Worker by ID", description = "Returns a single Worker", tags = { "Worker" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = WorkerDto.class))),
        @ApiResponse(responseCode = "404", description = "Worker not found") })
	@GetMapping("/{id}")
	public ResponseEntity<WorkerDto> findById(@PathVariable("id") String id) {
		Worker worker = service.findById(id);
		WorkerDto result = null;
		if (worker != null) {
			result = mapper.map(worker, WorkerDto.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Operation(summary = "Find all Workers", description = "", tags = { "Worker" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public ResponseEntity<List<WorkerDto>> findAll() {
		List<Worker> list = service.findAll();
		return new ResponseEntity<>(mapperUtil.convertToDtoList(list), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a Worker", description = "", tags = { "Worker" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "Worker not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<WorkerDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
		Worker worker = service.findById(id);
		if (worker == null) {
			throw new ResourceNotFoundException("Worker", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
