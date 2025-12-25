package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public Zone createZone(Zone zone) {
        zone.setActive(true);
        return repo.save(zone);
    }

    @Override
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    @Override
    public Zone updateZone(Long id, Zone updated) {
        Zone existing = getZoneById(id);

        existing.setZoneName(updated.getZoneName());
        existing.setPriorityLevel(updated.getPriorityLevel());
        existing.setPopulation(updated.getPopulation());

        return repo.save(existing);
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        zone.setActive(false);
        repo.save(zone);
    }
}
