package com.codeBeaters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.constant.Api;
import com.codeBeaters.entity.Event;
import com.codeBeaters.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(Api.USER)
public class EventController {

	@Autowired
	EventService service;
	
	@Operation(summary = "Creates a new Event")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Event created successfully"),
			     @ApiResponse(responseCode = "400",description = "Event is invalid")})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = "application/json",consumes = "application/json",value = "/createEvent")
	public String createEvent(@RequestBody Event c) {
		
		return service.createEvent(c);
	}
	
	@Operation(summary = "Read all Events")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Events Read successfully"),
			     @ApiResponse(responseCode = "400",description = "Event List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(produces = "application/json",value = "/getAll")
	public List<Event> getAll(){
		
		return service.getAll();
	}
	
	@Operation(summary = "Read Event By ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Event Read successfully"),
			     @ApiResponse(responseCode = "400",description = "Event List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(produces = "application/json",value = "/getById/{id}")
	public Optional<Event> getById(@PathVariable("id") int id){
		
		return service.getById(id);
	}
	
	@Operation(summary = "Delete a Event with id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Event deleted successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid Credentials"),
			@ApiResponse(responseCode = "404", description = "Event not found")})
	@DeleteMapping(value = "/deleteEvent/{id}")
	public String deleteEvent(@PathVariable("id") int id) {
		
		return service.deleteEvent(id);
	}
}
