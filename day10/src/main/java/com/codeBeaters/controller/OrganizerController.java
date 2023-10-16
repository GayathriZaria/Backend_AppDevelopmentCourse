package com.codeBeaters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.constant.Api;
import com.codeBeaters.entity.Organizer;
import com.codeBeaters.service.OrganizerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(Api.USER)
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizerController {
	
	@Autowired
	OrganizerService service;
	
	@Operation(summary = "Creates a new Organizer")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Organizer created successfully"),
			     @ApiResponse(responseCode = "400",description = "Data is invalid")})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = "application/json",consumes = "application/json",value = "/createOrganizer")
	public String createOrganizer(@RequestBody Organizer i) {
		
		return service.createOrganizer(i);
	}
	
	@Operation(summary = "Read all Organizers")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Organizers Read successfully"),
			     @ApiResponse(responseCode = "400",description = "Organizer List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/getallOrganizer")
	public List<Organizer> getOrganizers(){
		
		return service.getOrganizers();
	}

}
