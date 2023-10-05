package com.example.eventManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class EventModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String name;
	
	private String eventName;
	private String place;
    
	public int getId() {
		return Id;
	}



	public void setId(int Id) {
		this.Id = Id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}



	


	public EventModel(int Id, String name, String eventName, String place) {
		super();
		this.Id = Id;
		this.name = name;
		this.eventName = eventName;
		this.place = place;
	}




	public EventModel()
	{
		
	}
}