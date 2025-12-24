package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.DemandReadingService;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository readingRepository;
    private final ZoneRepository zoneRepository;

    // ✅ Constructor injection (ORDER MATTERS FOR TESTS)
    public DemandReadingServiceImpl(
            DemandReadingRepository readingRepository,
            ZoneRepository zoneRepository) {
        this.readingRepository = readingRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public DemandReading createReading(DemandReading reading) {

        // zone must exist
        Long zoneId = reading.getZone().getId();
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        // demand must be >= 0
        if (reading.getDemandMW() < 0) {
            throw new BadRequestException("demandMW must be >= 0");
        }

        // timestamp must not be future
        if (reading.getRecordedAt().isAfter(Instant.now())) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }

        reading.setZone(zone);
        return readingRepository.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {

        if (!zoneRepository.existsById(zoneId)) {
            throw new ResourceNotFoundException("Zone not found");
        }

        return readingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {

        if (!zoneRepository.existsById(zoneId)) {
            throw new ResourceNotFoundException("Zone not found");
        }

        return readingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No readings"));
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {

        if (!zoneRepository.existsById(zoneId)) {
            throw new ResourceNotFoundException("Zone not found");
        }

        List<DemandReading> readings =
                readingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);

        return readings.size() > limit
                ? readings.subList(0, limit)
                : readings;
    }
}
