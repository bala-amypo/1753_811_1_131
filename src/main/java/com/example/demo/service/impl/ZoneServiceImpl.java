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

    @Override
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = zoneRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        existing.setActive(zone.isActive());

        return zoneRepository.save(existing);
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }
}
