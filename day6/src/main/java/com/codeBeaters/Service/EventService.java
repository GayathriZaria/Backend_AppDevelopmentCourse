package com.codeBeaters.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBeaters.Modal.Event;
import com.codeBeaters.Repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.get();
    }

    public Event updateEvent(int eventId, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setStartTime(updatedEvent.getStartTime());
            existingEvent.setEndTime(updatedEvent.getEndTime());
            existingEvent.setOrganizer(updatedEvent.getOrganizer());
            existingEvent.setLocation(updatedEvent.getLocation());
            return eventRepository.save(existingEvent);
        } else {
            
        	return null;
        }
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }
}
