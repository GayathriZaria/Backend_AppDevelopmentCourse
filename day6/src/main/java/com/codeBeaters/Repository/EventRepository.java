package com.codeBeaters.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeBeaters.Modal.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
