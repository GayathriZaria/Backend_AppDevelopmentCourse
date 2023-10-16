package com.codeBeaters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.entity.Feedback;
import com.codeBeaters.service.FeedbackService;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
	
	@Autowired
	FeedbackService service;
	
	@PostMapping("/post")
	public ResponseEntity<String> postFeedback(@RequestBody Feedback f) {
		
		service.postFeedback(f);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Feedback>> getFeedbacks(){
		
		List<Feedback> feedback = service.getFeedbacks();
		return ResponseEntity.ok(feedback);
	}

}
