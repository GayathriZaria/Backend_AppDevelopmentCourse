package com.codeBeaters.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.codeBeaters.Modal.Event;
import com.codeBeaters.Service.EventService;

import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("/api/events") // Define the base URL path for event-related operations
public class EventController {

    private final EventService eventService; // Inject the EventService

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Endpoint for creating a new event
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // Endpoint for retrieving all events
    @GetMapping("/get")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Endpoint for retrieving a specific event by ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") int eventId) {
        return eventService.getEventById(eventId);
    }

    // Endpoint for updating an existing event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable("id") int eventId, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(eventId, updatedEvent);
    }

    // Endpoint for deleting a event by ID
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") int eventId) {
        eventService.deleteEvent(eventId);
    }
}
