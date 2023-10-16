package com.codeBeaters.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int event_id;
	private String eventName;
	private String duration;
	
	@ManyToOne
	private Organizer instructor_id;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int event_id, String eventName, String duration, Organizer instructor_id) {
		super();
		this.event_id = event_id;
		this.eventName = eventName;
		this.duration = duration;
		this.instructor_id = instructor_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Organizer getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(Organizer instructor_id) {
		this.instructor_id = instructor_id;
	}

}
