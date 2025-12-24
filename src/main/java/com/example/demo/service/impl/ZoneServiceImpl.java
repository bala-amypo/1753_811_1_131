package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    // ✅ Constructor injection (tests REQUIRE this exact constructor)
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    // ✅ CREATE ZONE
    @Override
    public Zone createZone(Zone zone) {

        // priority validation
        if (zone.getPriorityLevel() < 1) {
            throw new BadRequestException("priorityLevel must be >= 1");
        }

        // unique zoneName check
        zoneRepository.findByZoneName(zone.getZoneName())
                .ifPresent(z -> {
                    throw new BadRequestException("zoneName must be unique");
                });

        // defaults
        zone.setActive(true);
        zone.setCreatedAt(Instant.now());
        zone.setUpdatedAt(Instant.now());

        return zoneRepository.save(zone);
    }

    // ✅ UPDATE ZONE
    @Override
    public Zone updateZone(Long id, Zone zone) {

        Zone existing = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (zone.getPriorityLevel() < 1) {
            throw new BadRequestException("priorityLevel must be >= 1");
        }

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        existing.setUpdatedAt(Instant.now());

        return zoneRepository.save(existing);
    }

    // ✅ GET ZONE BY ID
    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    // ✅ GET ALL ZONES
    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // ✅ DEACTIVATE ZONE
    @Override
    public void deactivateZone(Long id) {

        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zone.setActive(false);
        zone.setUpdatedAt(Instant.now());

        zoneRepository.save(zone);
    }
}
