package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
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

    // CREATE
    @Override
    public Zone createZone(Zone zone) {
        zone.setActive(true);
        return zoneRepository.save(zone);
    }

    // GET BY ID
    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    // GET ALL
    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // UPDATE
    @Override
    public Zone updateZone(Long id, Zone updatedZone) {
        Zone zone = zoneRepository.findById(id).orElse(null);

        if (zone == null) return null;

        zone.setZoneName(updatedZone.getZoneName());
        zone.setPriorityLevel(updatedZone.getPriorityLevel());
        zone.setPopulation(updatedZone.getPopulation());

        return zoneRepository.save(zone);
    }

    // ✅ DEACTIVATE (YOU ASKED THIS)
    @Override
    public void deactivateZone(Long id) {
        Zone zone = zoneRepository.findById(id).orElse(null);
        if (zone != null) {
            zone.setActive(false);
            zoneRepository.save(zone);
        }
    }
}
