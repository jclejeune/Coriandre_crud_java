package com.coriandre.controller;

import com.coriandre.dto.OrganisationDTO;
import com.coriandre.mapper.OrganisationMapper;
import com.coriandre.service.OrganisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganisationDTO>> getAll() {
        List<OrganisationDTO> organisations = organisationService.getAll()
            .stream()
            .map(OrganisationMapper::toDTO)
            .toList();
        return ResponseEntity.ok(organisations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDTO> getById(@PathVariable Long id) {
        try {
            OrganisationDTO organisation = OrganisationMapper.toDTO(organisationService.getById(id));
            return ResponseEntity.ok(organisation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrganisationDTO> create(@RequestBody OrganisationDTO organisationDTO) {
        var organisation = OrganisationMapper.toEntity(organisationDTO);
        var savedOrganisation = organisationService.save(organisation);
        return ResponseEntity.ok(OrganisationMapper.toDTO(savedOrganisation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganisationDTO> update(@PathVariable Long id, @RequestBody OrganisationDTO organisationDTO) {
        try {
            var existingOrganisation = organisationService.getById(id);
            existingOrganisation.setName(organisationDTO.name());
            existingOrganisation.setDescription(organisationDTO.description());
            existingOrganisation.setAddress(organisationDTO.address());
            existingOrganisation.setLatitude(organisationDTO.latitude());
            existingOrganisation.setLongitude(organisationDTO.longitude());
            
            var updatedOrganisation = organisationService.save(existingOrganisation);
            return ResponseEntity.ok(OrganisationMapper.toDTO(updatedOrganisation));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            organisationService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}