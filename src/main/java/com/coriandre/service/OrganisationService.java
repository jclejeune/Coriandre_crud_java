// OrganisationService.java
package com.coriandre.service;

import com.coriandre.model.Organisation;
import com.coriandre.repository.OrganisationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrganisationService {
    private final OrganisationRepository repo;

    public OrganisationService(OrganisationRepository repo) {
        this.repo = repo;
    }

    public List<Organisation> getAll() { return repo.findAll(); }

    public Organisation getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organisation not found"));
    }

    public Organisation save(Organisation orga) { return repo.save(orga); }

    public void delete(Long id) { repo.deleteById(id); }
}
