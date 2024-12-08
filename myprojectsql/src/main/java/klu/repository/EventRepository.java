package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import klu.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
