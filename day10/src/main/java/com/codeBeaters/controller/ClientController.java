package com.codeBeaters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.constant.Api;
import com.codeBeaters.entity.Event;
import com.codeBeaters.entity.Client;
import com.codeBeaters.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(Api.USER)
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@Operation(summary = "Enroll a new Event")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Enrolled successfully"),
			     @ApiResponse(responseCode = "400",description = "Event is Already Enrolled")})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = "application/json",consumes = "application/json",value = "/enrollEvent")
	public String enrollEvent(@RequestBody Client s) {
		
		return service.enrollEvent(s);
	}
	
	@Operation(summary = "Read all Enrolled Event")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Event Read successfully"),
			     @ApiResponse(responseCode = "400",description = "Event List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(produces = "application/json",value = "/getEnrolled/{id}")
	public List<Event> getEnrolled(@PathVariable("id") long id){
		
		return service.getEnrolled(id);
	}
	
	@GetMapping("/getEnrolled/count/{id}")
	public int countEnrolled(@PathVariable("id") long id) {
		
		return service.countEnrolled(id);
	}

}
