package com.codeBeaters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.constant.Api;
import com.codeBeaters.entity.User;
import com.codeBeaters.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(Api.USER)
public class UserController {
	
	@Autowired
	UserService service;
	
	@Operation(summary = "Read User By ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "User Read successfully"),
			     @ApiResponse(responseCode = "400",description = "User List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(produces = "application/json",value = "/getByUid/{uid}")
	public Optional<User> getById(@PathVariable("uid") long uid){
		
		return service.getById(uid);
	}
	
	@Operation(summary = "Read Users")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "User Read successfully"),
			     @ApiResponse(responseCode = "400",description = "User List is empty Or Invalid Request")})
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(produces = "application/json",value = "/getAllUser")
	public List<User> getAllUser(){
		
		return service.getAllUser();
	}
	
	@Operation(summary = "Update an User with id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "User updated successfully"),
			              @ApiResponse(responseCode = "400",description = "Invalid Credentials"),
			              @ApiResponse(responseCode = "401",description = "User not found")})
	@PutMapping(produces = "application/json",consumes = "application/json",value = "/updateUser/{id}")
	public String update(@RequestBody User u,@PathVariable("id") long id) {
		
		return service.update(u,id);
	}
	
	@Operation(summary = "Delete an User with id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User deleted successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid Credentials"),
			@ApiResponse(responseCode = "404", description = "User not found")})
	@DeleteMapping(value = "/deleteUser/{id}")
	public String delete(@PathVariable("id") long id) {
		
		return service.delete(id);
	}
}
