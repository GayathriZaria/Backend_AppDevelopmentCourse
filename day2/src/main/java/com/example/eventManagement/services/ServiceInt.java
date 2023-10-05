package com.example.eventManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventManagement.model.EventModel;
import com.example.eventManagement.repositor.Repositor;
import com.example.eventManagement.serviceInt.Services;

@Service
public class ServiceInt implements Services{
	
	@Autowired
	private Repositor repo;

	@Override
	public List<EventModel> getClient() {
		return (List<EventModel>)repo.findAll();
	}

	@Override
	public EventModel createClient(EventModel model) {
		return repo.save(model);
	}

	@Override
	public void deleteClient(Integer Id) {
		repo.deleteById(Id);
	}

	@Override
	public void updateClient(EventModel model) {
		repo.save(model);
	}

	@Override
	public boolean isClientExist(String name) {
		return repo.existsByNameIgnoreCase(name);
	}

	@Override
	public boolean isClientExistbyid(int Id) {
		return repo.existsById(Id);
	}

	@Override
	public EventModel getClientbyid(int Id) {
		Optional<EventModel> optional = repo.findById(Id);
		EventModel model = optional.get();
		return model;
	}
	
	@Override
	public List<EventModel> getClientbyname(String name) 
	{
		return (List<EventModel>) repo.findByNameContainingIgnoreCase(name);
	}
	
}