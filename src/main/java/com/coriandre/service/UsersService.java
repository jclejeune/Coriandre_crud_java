// UsersService.java
package com.coriandre.service;

import com.coriandre.model.Users;
import com.coriandre.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {
    private final UsersRepository repo;

    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    public List<Users> getAll() { return repo.findAll(); }

    public Users getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users save(Users user) { return repo.save(user); }

    public void delete(Long id) { repo.deleteById(id); }
}
