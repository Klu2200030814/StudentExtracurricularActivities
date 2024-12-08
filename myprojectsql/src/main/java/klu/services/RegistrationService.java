package klu.services;

import klu.model.Registration;
import klu.repository.EventRegistrationRepository;
import klu.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    // Check if the user has already registered for the event
    public boolean hasUserRegistered(Long eventId, String userId) {
        List<Registration> registrations = eventRegistrationRepository.findByEventIdAndUserId(eventId, userId);
        return !registrations.isEmpty(); // Return true if there are any registrations
    }

    // Save the registration
    public Registration saveRegistration(Registration registration) {
        return eventRegistrationRepository.save(registration);
    }
}