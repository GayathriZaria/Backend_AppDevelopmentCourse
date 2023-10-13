package com.codeBeaters.Modal;

import java.util.Date; 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private String title;
    private Date startTime;
    private Date endTime;
    private String organizer;
    private String location;

    // Constructors
    public Event(int eventId, String title, Date startTime, Date endTime, String organizer, String location) {
        this.eventId = eventId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.location = location;
    }

    // Getter and Setter methods
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", title=" + title + ", startTime=" + startTime + ", endTime="
                + endTime + ", organizer=" + organizer + ", location=" + location + "]";
    }

	public Event() {
		super();
	}
}
