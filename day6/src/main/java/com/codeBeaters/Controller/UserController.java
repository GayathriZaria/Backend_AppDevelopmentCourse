package com.codeBeaters.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codeBeaters.DTO.AuthenticationResponse;
import com.codeBeaters.DTO.RegisterRequest;
import com.codeBeaters.DTO.UserDTO;
import com.codeBeaters.Modal.User;
import com.codeBeaters.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users") 
public class UserController {

	@Autowired
	private UserService userService; 
	
	
	@GetMapping("/get")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	
	@GetMapping("/get/{id}")
	public User getUserById(@PathVariable("id") int userId) {
		return userService.getUserById(userId);
	}

	
	
	@PutMapping("/put/{id}")
	public User updateUser(@PathVariable("id") int userId, @RequestBody User updatedUser) {
		return userService.updateUser(userId, updatedUser);
	}

	
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") int userId) {
		userService.deleteUser(userId);
	}
	
	
}
