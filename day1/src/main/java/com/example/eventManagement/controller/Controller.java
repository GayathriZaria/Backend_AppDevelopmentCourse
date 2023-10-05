package com.example.eventManagement.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventManagement.model.EventModel;
import com.example.eventManagement.serviceInt.Services;

@RestController
@RequestMapping(value="/client")
@CrossOrigin("*")
public class Controller{
	@Autowired
	private Services service;
	
	@GetMapping(value="/getall")
	private ResponseEntity<Object> getClient() 
	{
		List<EventModel> list = service.getClient();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value="/getbyid/{Id}")
	private ResponseEntity<Object> getClientbyid(@PathVariable int Id) 
	{
		boolean isClientExistbyid = service.isClientExistbyid(Id);
		if (isClientExistbyid)
		{
			EventModel model = service.getClientbyid(Id);
		return new ResponseEntity<>(model, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("ID not found",HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/getbyname/{Name}")
	private ResponseEntity<Object> getClientbyname(@PathVariable String name) 
	{
		List<EventModel> model = service.getClientbyname(name);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping(value="/add")
	public String createClient(@RequestBody EventModel model)
	{
		boolean isClientExist = service.isClientExist(model.getName());
		if(isClientExist)
			return "Client exists already";
		else {
			model = service.createClient(model);
		return "Client added";}
	}
		
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<Object> deleteClient(@RequestParam int Id)
	{
		service.deleteClient(Id);
			return new ResponseEntity<>("R.I.P."
					+ "\nCause of Death : ClientPageModel Deletion", HttpStatus.OK);
	}
	
	
	@PutMapping(value="/edit/{Id}")
	public ResponseEntity<Object> updateClient(@PathVariable int Id, @RequestBody EventModel model)
	{
			model.setId(Id);
			service.updateClient(model);
			return new ResponseEntity<>("ClientPageModel details are updated successsfully !", HttpStatus.OK);
	}
	
}