package com.coriandre.mapper;

import com.coriandre.dto.EventDTO;
import com.coriandre.model.Event;

public class EventMapper {

    public static EventDTO toDTO(Event event) {
        if (event == null) {
            return null;
        }
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setStartDate(event.getStartDate());
        dto.setEndDate(event.getEndDate());
        dto.setLatitude(event.getLatitude());
        dto.setLongitude(event.getLongitude());

        if (event.getOrganisation() != null) {
            dto.setOrganisationId(event.getOrganisation().getId());
        }

        return dto;
    }

    public static Event toEntity(EventDTO dto) {
        if (dto == null) {
            return null;
        }
        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        event.setLatitude(dto.getLatitude());
        event.setLongitude(dto.getLongitude());
        return event;
    }
}
