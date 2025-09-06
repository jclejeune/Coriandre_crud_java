// EventDTO.java
package com.coriandre.dto;

import java.time.LocalDateTime;

public record EventDTO(
        Long id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double latitude,
        Double longitude,
        Long organisationId
) {}
