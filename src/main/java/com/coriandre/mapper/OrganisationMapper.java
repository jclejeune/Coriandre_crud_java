package com.coriandre.mapper;

import com.coriandre.dto.OrganisationDTO;
import com.coriandre.model.Organisation;

public class OrganisationMapper {

    public static OrganisationDTO toDTO(Organisation organisation) {
        if (organisation == null) {
            return null;
        }
        
        return new OrganisationDTO(
            organisation.getId(),
            organisation.getName(),
            organisation.getDescription(),
            organisation.getAddress(),
            organisation.getLatitude(),
            organisation.getLongitude()
        );
    }

    public static Organisation toEntity(OrganisationDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Organisation organisation = new Organisation();
        organisation.setId(dto.id());
        organisation.setName(dto.name());
        organisation.setDescription(dto.description());
        organisation.setAddress(dto.address());
        organisation.setLatitude(dto.latitude());
        organisation.setLongitude(dto.longitude());
        return organisation;
    }
}