package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.data.domain.PageRequest;
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
    public DemandReading create(DemandReading reading) {
        reading.setRecordedAt(Instant.now());
        return repo.save(reading);
    }

    @Override
    public List<DemandReading> getByZone(Long zoneId) {
        return repo.findByZoneId(zoneId);
    }

    @Override
    public List<DemandReading> getRecentByZone(Long zoneId) {
        return repo.findByZoneIdOrderByRecordedAtDesc(
                zoneId,
                PageRequest.of(0, 5)
        );
    }

    @Override
    public DemandReading getLatestByZone(Long zoneId) {
        List<DemandReading> list =
                repo.findByZoneIdOrderByRecordedAtDesc(
                        zoneId,
                        PageRequest.of(0, 1)
                );
        return list.isEmpty() ? null : list.get(0);
    }
}
