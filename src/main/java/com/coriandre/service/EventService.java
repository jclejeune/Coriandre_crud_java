package com.coriandre.service;

import com.coriandre.dto.EventDTO;
import com.coriandre.mapper.EventMapper;
import com.coriandre.model.Event;
import com.coriandre.repository.EventRepository;
import com.coriandre.repository.OrganisationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final OrganisationRepository organisationRepository;

    public EventService(EventRepository eventRepository, OrganisationRepository organisationRepository) {
        this.eventRepository = eventRepository;
        this.organisationRepository = organisationRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event createEvent(EventDTO eventDTO) {
        Event event = EventMapper.toEntity(eventDTO);
        
        // Associer l'organisation si fournie
        if (eventDTO.organisationId() != null) {
            var organisation = organisationRepository.findById(eventDTO.organisationId())
                .orElseThrow(() -> new RuntimeException("Organisation not found with id: " + eventDTO.organisationId()));
            event.setOrganisation(organisation);
        }
        
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = getById(id);
        
        existingEvent.setName(eventDTO.name());
        existingEvent.setDescription(eventDTO.description());
        existingEvent.setStartDate(eventDTO.startDate());
        existingEvent.setEndDate(eventDTO.endDate());
        existingEvent.setLatitude(eventDTO.latitude());
        existingEvent.setLongitude(eventDTO.longitude());

        // Mettre à jour l'organisation si fournie
        if (eventDTO.organisationId() != null) {
            var organisation = organisationRepository.findById(eventDTO.organisationId())
                .orElseThrow(() -> new RuntimeException("Organisation not found with id: " + eventDTO.organisationId()));
            existingEvent.setOrganisation(organisation);
        } else {
            existingEvent.setOrganisation(null);
        }

        return eventRepository.save(existingEvent);
    }

    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}