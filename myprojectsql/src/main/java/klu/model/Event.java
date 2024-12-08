package klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;
    private String eventDescription;
    private LocalDateTime cur_time; // Current time when event is created
    private LocalDateTime registrationStartingTime;
    private LocalDateTime registrationEndingTime;
    private LocalDateTime eventScheduleTime;
    private int eventLimit; // Event participant limit
    private String eventCoverPhoto; // URL to the event cover photo

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getCur_time() {
        return cur_time;
    }

    public void setCur_time(LocalDateTime cur_time) {
        this.cur_time = cur_time;
    }

    public LocalDateTime getRegistrationStartingTime() {
        return registrationStartingTime;
    }

    public void setRegistrationStartingTime(LocalDateTime registrationStartingTime) {
        this.registrationStartingTime = registrationStartingTime;
    }

    public LocalDateTime getRegistrationEndingTime() {
        return registrationEndingTime;
    }

    public void setRegistrationEndingTime(LocalDateTime registrationEndingTime) {
        this.registrationEndingTime = registrationEndingTime;
    }

    public LocalDateTime getEventScheduleTime() {
        return eventScheduleTime;
    }

    public void setEventScheduleTime(LocalDateTime eventScheduleTime) {
        this.eventScheduleTime = eventScheduleTime;
    }

    public int getEventLimit() {
        return eventLimit;
    }

    public void setEventLimit(int eventLimit) {
        this.eventLimit = eventLimit;
    }

    public String getEventCoverPhoto() {
        return eventCoverPhoto;
    }

    public void setEventCoverPhoto(String eventCoverPhoto) {
        this.eventCoverPhoto = eventCoverPhoto;
    }
}
