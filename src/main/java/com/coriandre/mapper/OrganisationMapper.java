package com.coriandre.mapper;

import com.coriandre.dto.OrganisationDTO;
import com.coriandre.model.Organisation;

public class OrganisationMapper {

    public static OrganisationDTO toDTO(Organisation organisation) {
        if (organisation == null) {
            return null;
        }
        OrganisationDTO dto = new OrganisationDTO();
        dto.setId(organisation.getId());
        dto.setName(organisation.getName());
        return dto;
    }

    public static Organisation toEntity(OrganisationDTO dto) {
        if (dto == null) {
            return null;
        }
        Organisation organisation = new Organisation();
        organisation.setId(dto.getId());
        organisation.setName(dto.getName());
        return organisation;
    }
}
