package com.codeBeaters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBeaters.entity.Community;
import com.codeBeaters.repository.CommunityRepo;

@Service
public class CommunityService {
	
	@Autowired
	CommunityRepo repo;
	
	public String createPost(Community c) {
		
		repo.save(c);
		return c.getPost();
	}
	
	public List<String> getComment(int id){
		
		Community c = repo.findById(id).get();
		return c.getComments();
	}
	
	public Community updatePost(int id,Community c) {
		
		Community updateC = repo.findById(id).get();
		updateC.setPost(c.getPost());
		
		return updateC;
	}
	
	public String deletePost(int id) {
		
		repo.deleteById(id);
		return "Deleted . . .";
	}

}
