package com.coriandre.controller;

import com.coriandre.dto.UsersDTO;
import com.coriandre.mapper.UsersMapper;
import com.coriandre.model.Role;
import com.coriandre.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAll() {
        List<UsersDTO> users = usersService.getAll()
            .stream()
            .map(UsersMapper::toDTO)
            .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getById(@PathVariable Long id) {
        try {
            UsersDTO user = UsersMapper.toDTO(usersService.getById(id));
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody CreateUserRequest request) {
        try {
            var user = usersService.createUser(request);
            return ResponseEntity.ok(UsersMapper.toDTO(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> update(@PathVariable Long id, @RequestBody UsersDTO userDTO) {
        try {
            var existingUser = usersService.getById(id);
            existingUser.setUsername(userDTO.username());
            existingUser.setEmail(userDTO.email());
            if (userDTO.role() != null) {
                existingUser.setRole(Role.valueOf(userDTO.role()));
            }
            
            var updatedUser = usersService.save(existingUser);
            return ResponseEntity.ok(UsersMapper.toDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usersService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Record pour la création d'utilisateur (inclut le password)
    public record CreateUserRequest(
        String username,
        String email,
        String password,
        String role,
        Long organisationId
    ) {}
}