package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    // ⚠️ constructor order MUST match tests
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone createZone(Zone zone) {

        // 1️⃣ priority validation FIRST
        if (zone.getPriorityLevel() == null || zone.getPriorityLevel() < 1) {
            throw new BadRequestException("priorityLevel must be >= 1");
        }

        // 2️⃣ unique name check
        zoneRepository.findByZoneName(zone.getZoneName())
                .ifPresent(z -> {
                    throw new BadRequestException("zoneName must be unique");
                });

        // 3️⃣ defaults
        if (zone.getActive() == null) {
            zone.setActive(true);
        }

        zone.setCreatedAt(Instant.now());
        zone.setUpdatedAt(Instant.now());

        return zoneRepository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {

        Zone existing = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        // priority validation
        if (zone.getPriorityLevel() == null || zone.getPriorityLevel() < 1) {
            throw new BadRequestException("priorityLevel must be >= 1");
        }

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        existing.setUpdatedAt(Instant.now());

        return zoneRepository.save(existing);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public void deactivateZone(Long id) {

        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zone.setActive(false);
        zone.setUpdatedAt(Instant.now());

        zoneRepository.save(zone);
    }
}
