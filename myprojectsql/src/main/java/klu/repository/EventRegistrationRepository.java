package klu.repository;

import klu.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    long countByEventId(Long eventId); // Count registrations by event ID
	List<Registration> findByEventIdAndUserId(Long eventId, String userId);
    
}