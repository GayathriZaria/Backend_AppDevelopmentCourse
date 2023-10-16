package com.codeBeaters.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBeaters.entity.Event;
import com.codeBeaters.repository.EventRepo;

@Service
public class EventService {
	
	@Autowired
	EventRepo event;
	
	public String createEvent(Event c) {
		
		event.save(c);
		return "Posted. . .";
	}
	
	public List<Event> getAll(){
		
		return event.findAll();
	}
	
	public Optional<Event> getById(int id) {
		
		return event.findById(id);
	}
	
	public String deleteEvent(int id) {
		
		event.deleteById(id);
		return "Events Deleted. . .";
	}

}
