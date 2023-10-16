package com.codeBeaters.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	private long client_id;
	private String name;
	@ManyToMany
	private List<Event> enrolledEvents;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(long client_id, String name, List<Event> enrolledEvents) {
		super();
		this.client_id = client_id;
		this.name = name;
		this.enrolledEvents = enrolledEvents;
	}
	public long getClient_id() {
		return client_id;
	}
	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Event> getEnrolledEvents() {
		return enrolledEvents;
	}
	public void setEnrolledEvents(List<Event> enrolledEvents) {
		this.enrolledEvents = enrolledEvents;
	}

}
