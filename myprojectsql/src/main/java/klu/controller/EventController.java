package klu.controller;

import klu.model.Event;
import klu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    private static final String UPLOAD_DIR = "C:/Users/DEEPAK/Downloads/Telegram Desktop/deepakjfsd/Eventmanagement/uploads/";

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(
            @RequestParam("eventTitle") String eventTitle,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("registrationStartingTime") LocalDateTime registrationStartingTime,
            @RequestParam("registrationEndingTime") LocalDateTime registrationEndingTime,
            @RequestParam("eventScheduleTime") LocalDateTime eventScheduleTime,
            @RequestParam("limit") int limit,
            @RequestParam("eventCoverPhoto") MultipartFile eventCoverPhoto) {

        // Handle file upload
        String fileName = eventCoverPhoto.getOriginalFilename();
        File destinationFile = new File(UPLOAD_DIR + File.separator + fileName);

        try {
            eventCoverPhoto.transferTo(destinationFile);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Generate file URL for database storage
        String fileUrl = "/uploads/" + fileName;

        // Create event entity and set its properties
        Event event = new Event();
        event.setEventTitle(eventTitle);
        event.setEventDescription(eventDescription);
        event.setCur_time(LocalDateTime.now());
        event.setRegistrationStartingTime(registrationStartingTime);
        event.setRegistrationEndingTime(registrationEndingTime);
        event.setEventScheduleTime(eventScheduleTime);
        event.setEventLimit(limit);
        event.setEventCoverPhoto(fileUrl);

        // Save event in database
        eventService.addEvent(event);

        return new ResponseEntity<>("Event created successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Event deleted successfully!", HttpStatus.OK);
    }

    // Updated PUT method for updating an existing event
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(
            @PathVariable Long id,
            @RequestParam("eventTitle") String eventTitle,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("registrationStartingTime") LocalDateTime registrationStartingTime,
            @RequestParam("registrationEndingTime") LocalDateTime registrationEndingTime,
            @RequestParam("eventScheduleTime") LocalDateTime eventScheduleTime,
            @RequestParam("limit") int limit,
            @RequestParam(value = "eventCoverPhoto", required = false) MultipartFile eventCoverPhoto) {

        // Find the existing event
        Event existingEvent = eventService.getEventById(id);
        if (existingEvent == null) {
            return new ResponseEntity<>("Event not found!", HttpStatus.NOT_FOUND);
        }

        // Update fields
        existingEvent.setEventTitle(eventTitle);
        existingEvent.setEventDescription(eventDescription);
        existingEvent.setRegistrationStartingTime(registrationStartingTime);
        existingEvent.setRegistrationEndingTime(registrationEndingTime);
        existingEvent.setEventScheduleTime(eventScheduleTime);
        existingEvent.setEventLimit(limit);

        // Handle file upload if provided
        if (eventCoverPhoto != null) {
            String fileName = eventCoverPhoto.getOriginalFilename();
            File destinationFile = new File(UPLOAD_DIR + File.separator + fileName);

            try {
                eventCoverPhoto.transferTo(destinationFile);
                String fileUrl = "/uploads/" + fileName;
                existingEvent.setEventCoverPhoto(fileUrl);
            } catch (IOException e) {
                return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Save updated event in database
        eventService.updateEvent(existingEvent);

        return new ResponseEntity<>("Event updated successfully!", HttpStatus.OK);
    }
}
