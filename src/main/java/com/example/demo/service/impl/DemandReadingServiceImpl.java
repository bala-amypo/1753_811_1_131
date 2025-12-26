package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.DemandReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;
    private final ZoneRepository zoneRepo;

    @Override
    public DemandReading createReading(DemandReading reading) {
        if (reading.getDemandMW() < 0)
            throw new BadRequestException("Demand must be >= 0");

        if (reading.getRecordedAt().isAfter(Instant.now()))
            throw new BadRequestException("Recorded time cannot be in future");

        Long zoneId = reading.getZone().getId();
        Zone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        reading.setZone(zone);
        return repo.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        if (zoneRepo.findById(zoneId).isEmpty())
            throw new ResourceNotFoundException("Zone not found");

        return repo.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> list = getReadingsForZone(zoneId);
        return list.subList(0, Math.min(limit, list.size()));
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return repo.findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No readings"));
    }
}
