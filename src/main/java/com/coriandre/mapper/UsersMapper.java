package com.coriandre.mapper;

import com.coriandre.dto.UsersDTO;
import com.coriandre.model.Users;

public class UsersMapper {

    public static UsersDTO toDTO(Users users) {
        if (users == null) {
            return null;
        }
        
        return new UsersDTO(
            users.getId(),
            users.getUsername(),
            users.getEmail(),
            users.getRole() != null ? users.getRole().toString() : null
        );
    }

    public static Users toEntity(UsersDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Users users = new Users();
        users.setId(dto.id());
        users.setUsername(dto.username());
        users.setEmail(dto.email());
        // Le rôle et le mot de passe seront définis dans le service
        // ⚠️ password volontairement ignoré côté DTO pour la sécurité
        return users;
    }
}