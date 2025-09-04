package com.coriandre.controller;

import com.coriandre.model.Event;
import com.coriandre.model.Organisation;
import com.coriandre.repository.EventRepository;
import com.coriandre.repository.OrganisationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;
    private final OrganisationRepository organisationRepository;

    public EventController(EventRepository eventRepository, OrganisationRepository organisationRepository) {
        this.eventRepository = eventRepository;
        this.organisationRepository = organisationRepository;
    }

    @GetMapping
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Event create(@RequestBody Event event) {
        if (event.getOrganisation() != null) {
            Long orgId = event.getOrganisation().getId();
            Optional<Organisation> org = organisationRepository.findById(orgId);
            org.ifPresent(event::setOrganisation);
        }
        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        Event existing = eventRepository.findById(id).orElseThrow();
        existing.setName(event.getName());
        existing.setDescription(event.getDescription());
        existing.setStartDate(event.getStartDate());
        existing.setEndDate(event.getEndDate());

        if (event.getOrganisation() != null) {
            Long orgId = event.getOrganisation().getId();
            organisationRepository.findById(orgId).ifPresent(existing::setOrganisation);
        }
        return eventRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
