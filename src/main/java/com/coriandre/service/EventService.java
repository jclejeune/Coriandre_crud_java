// EventService.java
package com.coriandre.service;

import com.coriandre.model.Event;
import com.coriandre.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public List<Event> getAll() { return repo.findAll(); }

    public Event getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event save(Event event) { return repo.save(event); }

    public void delete(Long id) { repo.deleteById(id); }
}
