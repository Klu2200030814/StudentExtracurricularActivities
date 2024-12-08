package klu.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.model.Event;
import klu.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Add event to the database
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events from the database
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get event by ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    // Delete event by ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    public void updateEvent(Event event) {
        eventRepository.save(event); // Save the updated event
    }
    
}
