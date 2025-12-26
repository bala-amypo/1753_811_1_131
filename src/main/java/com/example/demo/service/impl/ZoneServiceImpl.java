package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepo;

    @Override
    public Zone createZone(Zone zone) {
        if (zone.getPriorityLevel() == null || zone.getPriorityLevel() < 1)
            throw new BadRequestException("Priority must be >= 1");

        if (zoneRepo.findByZoneName(zone.getZoneName()).isPresent())
            throw new BadRequestException("Zone name must be unique");

        zone.setActive(true);
        zone.setUpdatedAt(Instant.now());  
        return zoneRepo.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone updated) {
        Zone zone = zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zone.setZoneName(updated.getZoneName());
        zone.setPriorityLevel(updated.getPriorityLevel());
        zone.setPopulation(updated.getPopulation());
        zone.setActive(updated.getActive());
        zone.setUpdatedAt(Instant.now());  

        return zoneRepo.save(zone);
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        zone.setActive(false);
        zoneRepo.save(zone);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepo.findAll();
    }
}
