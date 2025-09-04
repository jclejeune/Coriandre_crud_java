package com.coriandre.controller;

import com.coriandre.model.Organisation;
import com.coriandre.repository.OrganisationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationRepository organisationRepository;

    public OrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @GetMapping
    public List<Organisation> getAll() {
        return organisationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Organisation getById(@PathVariable Long id) {
        return organisationRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Organisation create(@RequestBody Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @PutMapping("/{id}")
    public Organisation update(@PathVariable Long id, @RequestBody Organisation organisation) {
        Organisation existing = organisationRepository.findById(id).orElseThrow();
        existing.setName(organisation.getName());
        existing.setDescription(organisation.getDescription());
        existing.setAddress(organisation.getAddress());
        existing.setLatitude(organisation.getLatitude());
        existing.setLongitude(organisation.getLongitude());
        return organisationRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        organisationRepository.deleteById(id);
    }
}
