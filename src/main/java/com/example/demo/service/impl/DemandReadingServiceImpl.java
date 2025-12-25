package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;

    public DemandReadingServiceImpl(DemandReadingRepository repo) {
        this.repo = repo;
    }

    @Override
    public DemandReading save(DemandReading reading) {
        reading.setRecordedAt(Instant.now());
        return repo.save(reading);
    }

    @Override
    public List<DemandReading> getByZone(Long zoneId) {
        return repo.findByZoneId(zoneId);
    }

    @Override
    public List<DemandReading> getRecent(Long zoneId) {
        return repo.findTop5ByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public DemandReading getLatest(Long zoneId) {
        return repo.findTopByZoneIdOrderByRecordedAtDesc(zoneId).orElse(null);
    }
}
