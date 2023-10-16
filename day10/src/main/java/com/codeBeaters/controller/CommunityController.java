package com.codeBeaters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.constant.Api;
import com.codeBeaters.entity.Community;
import com.codeBeaters.service.CommunityService;

@RestController
@RequestMapping(Api.USER)
public class CommunityController {
	
	@Autowired
	CommunityService service;
	
	@PostMapping("/createPost")
	public String createPost(Community c) {
		
		return service.createPost(c);
	}
	
	@GetMapping("/getComment/{id}")
	public List<String> getComment(@PathVariable("id") int id){
		
		return service.getComment(id);
	}
	
	@PutMapping("/updatePost/{id}")
	public Community updatePost(@PathVariable("id") int id,@RequestBody Community c) {
		
		return service.updatePost(id,c);
	}
	
	@DeleteMapping("/deletePost/{id}")
	public String deletePost(@PathVariable("id") int id) {
		
		return service.deletePost(id);
	}

}
