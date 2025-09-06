package com.coriandre.service;

import com.coriandre.controller.UsersController.CreateUserRequest;
import com.coriandre.model.Role;
import com.coriandre.model.Users;
import com.coriandre.repository.OrganisationRepository;
import com.coriandre.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final OrganisationRepository organisationRepository;

    public UsersService(UsersRepository usersRepository, OrganisationRepository organisationRepository) {
        this.usersRepository = usersRepository;
        this.organisationRepository = organisationRepository;
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Users getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }

    public Users createUser(CreateUserRequest request) {
        // Vérifier si l'utilisateur existe déjà
        if (usersRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists: " + request.username());
        }

        Users user = new Users();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password()); // En production, hasher le mot de passe !
        
        // Définir le rôle
        if (request.role() != null) {
            try {
                user.setRole(Role.valueOf(request.role().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role: " + request.role());
            }
        } else {
            user.setRole(Role.USERS); // Rôle par défaut
        }

        // Associer l'organisation si fournie
        if (request.organisationId() != null) {
            var organisation = organisationRepository.findById(request.organisationId())
                .orElseThrow(() -> new RuntimeException("Organisation not found with id: " + request.organisationId()));
            user.setOrganisation(organisation);
        }

        return usersRepository.save(user);
    }

    public void delete(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        usersRepository.deleteById(id);
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
}