// OrganisationDTO.java
package com.coriandre.dto;

public record OrganisationDTO(
        Long id,
        String name,
        String description,
        String address,
        double latitude,
        double longitude
) {}
