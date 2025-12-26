package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneRepositoryServiceImpl implements ZoneRepositoryService {

    private final ZoneRepository zoneRepository;

    public ZoneRepositoryServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Optional<Zone> findById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public Optional<Zone> findByZoneName(String zoneName) {
        return zoneRepository.findByZoneName(zoneName);
    }

    @Override
    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    public List<Zone> findActiveZonesByPriority() {
        return zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();
    }

    @Override
    public void deleteById(Long id) {
        zoneRepository.deleteById(id);
    }
}
