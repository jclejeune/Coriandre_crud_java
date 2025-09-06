package com.coriandre.mapper;

import com.coriandre.dto.EventDTO;
import com.coriandre.model.Event;

public class EventMapper {

    public static EventDTO toDTO(Event event) {
        if (event == null) {
            return null;
        }
        
        return new EventDTO(
            event.getId(),
            event.getName(),
            event.getDescription(),
            event.getStartDate(),
            event.getEndDate(),
            event.getLatitude(),
            event.getLongitude(),
            event.getOrganisation() != null ? event.getOrganisation().getId() : null
        );
    }

    public static Event toEntity(EventDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Event event = new Event();
        event.setId(dto.id());
        event.setName(dto.name());
        event.setDescription(dto.description());
        event.setStartDate(dto.startDate());
        event.setEndDate(dto.endDate());
        event.setLatitude(dto.latitude());
        event.setLongitude(dto.longitude());
        // Note: L'organisation sera définie dans le service
        return event;
    }
}