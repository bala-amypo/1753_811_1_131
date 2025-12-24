package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.*;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone createZone(Zone zone) {
        zoneRepository.findByZoneName(zone.getZoneName())
                .ifPresent(z -> { throw new BadRequestException("unique"); });

        if (zone.getPriorityLevel() < 1)
            throw new BadRequestException(">=0");

        zone.setActive(true);
        return zoneRepository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = getZoneById(id);
        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        return zoneRepository.save(existing);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
