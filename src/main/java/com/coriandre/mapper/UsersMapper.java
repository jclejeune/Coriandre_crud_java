package com.coriandre.mapper;

import com.coriandre.dto.UsersDTO;
import com.coriandre.model.Users;

public class UsersMapper {

    public static UsersDTO toDTO(Users users) {
        if (users == null) {
            return null;
        }
        UsersDTO dto = new UsersDTO();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setEmail(users.getEmail());

        if (users.getOrganisation() != null) {
            dto.setOrganisationId(users.getOrganisation().getId());
        }

        return dto;
    }

    public static Users toEntity(UsersDTO dto) {
        if (dto == null) {
            return null;
        }
        Users users = new Users();
        users.setId(dto.getId());
        users.setUsername(dto.getUsername());
        users.setEmail(dto.getEmail());
        // ⚠️ password volontairement ignoré côté DTO pour la sécurité
        return users;
    }
}
