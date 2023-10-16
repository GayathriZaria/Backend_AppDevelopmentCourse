package com.codeBeaters.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizer")
public class Organizer {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int organizer_id;
	private String organizerName;
	private String experience;
	private String teaching;
	
	public Organizer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organizer(int organizer_id, String organizerName, String experience, String teaching) {
		super();
		this.organizer_id = organizer_id;
		this.organizerName = organizerName;
		this.experience = experience;
		this.teaching = teaching;
	}
	public int getOrganizer_id() {
		return organizer_id;
	}
	public void setOrganizer_id(int organizer_id) {
		this.organizer_id = organizer_id;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getTeaching() {
		return teaching;
	}
	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}

}
