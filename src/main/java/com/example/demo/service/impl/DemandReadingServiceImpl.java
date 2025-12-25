package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;

    public DemandReadingServiceImpl(DemandReadingRepository repo) {
        this.repo = repo;
    }

    @Override
    public DemandReading create(DemandReading reading) {
        return repo.save(reading);
    }

    @Override
    public List<DemandReading> getByZone(Long zoneId) {
        return repo.findByZoneId(zoneId);
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int count) {
        return repo.findByZoneIdOrderByTimestampDesc(
                zoneId,
                PageRequest.of(0, count)
        );
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return repo.findTopByZoneIdOrderByTimestampDesc(zoneId);
    }
}
