package com.coriandre.controller;

import com.coriandre.dto.EventDTO;
import com.coriandre.mapper.EventMapper;
import com.coriandre.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        List<EventDTO> events = eventService.getAll()
            .stream()
            .map(EventMapper::toDTO)
            .toList();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        try {
            EventDTO event = EventMapper.toDTO(eventService.getById(id));
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody EventDTO eventDTO) {
        try {
            var savedEvent = eventService.createEvent(eventDTO);
            return ResponseEntity.ok(EventMapper.toDTO(savedEvent));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        try {
            var updatedEvent = eventService.updateEvent(id, eventDTO);
            return ResponseEntity.ok(EventMapper.toDTO(updatedEvent));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            eventService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}