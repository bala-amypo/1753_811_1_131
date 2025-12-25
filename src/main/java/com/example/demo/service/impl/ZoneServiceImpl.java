package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;

    public ZoneServiceImpl(ZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zone createZone(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        return repository.save(existing);
    }

    @Override
    public Zone getZone(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return repository.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = getZone(id);
        zone.setActive(false);
        repository.save(zone);
    }
}
