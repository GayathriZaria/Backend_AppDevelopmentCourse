
package com.codeBeaters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBeaters.entity.Event;
import com.codeBeaters.entity.Client;
import com.codeBeaters.repository.ClientRepo;

@Service
public class ClientService {
	
	@Autowired
	ClientRepo repo;
	
	public String enrollEvent(Client s) {
		
		List<Integer> count = repo.clientExist(s.getClient_id());		
		if(count.get(0) == 0) {
			
			repo.save(s);
			return "Enrolled. . .";
		}
		else {
			
		    repo.addEvents(s.getClient_id(),s.getEnrolledEvents().get(0).getEvent_id());
			return "Enrolled. . .";
		}
	}
	
	public List<Event> getEnrolled(long id){
		
		return repo.findById(id).get().getEnrolledEvents();
	}
	
	public int countEnrolled(long id) {
		
		 List<Event> list = repo.findById(id).get().getEnrolledEvents(); 
		 return list.size();
	}

}
