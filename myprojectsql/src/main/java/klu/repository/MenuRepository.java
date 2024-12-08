package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import klu.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
