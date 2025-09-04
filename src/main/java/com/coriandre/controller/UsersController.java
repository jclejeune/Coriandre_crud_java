package com.coriandre.controller;

import com.coriandre.model.Users;
import com.coriandre.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRepository userRepository;

    public UsersController(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users user) {
        Users existing = userRepository.findById(id).orElseThrow();
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRole(user.getRole());
        return userRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
