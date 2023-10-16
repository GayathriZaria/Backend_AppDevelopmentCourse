package com.codeBeaters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBeaters.entity.Organizer;
import com.codeBeaters.repository.OrganizerRepo;

@Service
public class OrganizerService {
	
	@Autowired
	OrganizerRepo repo;
	
	public String createOrganizer(Organizer i) {
		
		repo.save(i);
		return "Posted . . .";
	}
	
	public List<Organizer> getOrganizers(){
		
		return repo.findAll();
	}

}
