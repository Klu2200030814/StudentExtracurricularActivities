package klu.controller;

import klu.model.Registration;
import klu.model.Event;
import klu.services.RegistrationService;
import klu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EventService eventService;

    @PostMapping("/register")
    public String registerForEvent(@RequestParam Long eventId, @RequestParam String userId) {
        // Check if the user has already registered
        if (registrationService.hasUserRegistered(eventId, userId)) {
            return "You have already registered for this event.";
        }

        // Find the event (assume EventService fetches event by ID)
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return "Event not found.";
        }

        // Create a new registration
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUserId(userId);
        registration.setRegistrationTime(LocalDateTime.now());

        // Save the registration
        registrationService.saveRegistration(registration);

        return "Registration successful!";
    }
}
