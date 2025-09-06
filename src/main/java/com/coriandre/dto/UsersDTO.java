// UsersDTO.java
package com.coriandre.dto;

public record UsersDTO(
        Long id,
        String username,
        String email,
        String role
) {}
