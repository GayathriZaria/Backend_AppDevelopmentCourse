package com.example.eventManagement.serviceInt;

import java.util.List;

import com.example.eventManagement.model.EventModel;

public interface Services{

	public abstract List<EventModel> getClient();

	public abstract EventModel createClient(EventModel model);

	public abstract void updateClient(EventModel model);

	public abstract void deleteClient(Integer Id);

	public abstract boolean isClientExist(String name);

	public abstract boolean isClientExistbyid(int Id);

    public abstract EventModel getClientbyid(int Id);

    public abstract List<EventModel> getClientbyname(String name);
	
}