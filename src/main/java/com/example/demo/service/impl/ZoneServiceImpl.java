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
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public Zone getZoneById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Zone createZone(Zone zone) {
        return repo.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        zone.setId(id);
        return repo.save(zone);
    }

    @Override
    public void deactivateZone(Long id) {
        repo.findById(id).ifPresent(z -> {
            z.setActive(false);
            repo.save(z);
        });
    }
}
