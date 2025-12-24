package com.example.demo.service.impl;

import java.sql.Timestamp;
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

    private final DemandReadingRepository demandReadingRepository;
    private final ZoneRepository zoneRepository;

    public DemandReadingServiceImpl(
            DemandReadingRepository demandReadingRepository,
            ZoneRepository zoneRepository
    ) {
        this.demandReadingRepository = demandReadingRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public DemandReading createReading(DemandReading reading) {

        if (reading.getDemandMW() == null || reading.getDemandMW() < 0) {
            throw new BadRequestException(">=0");
        }

        if (reading.getRecordedAt() != null &&
            reading.getRecordedAt().after(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("future");
        }

        Long zoneId = reading.getZone().getId();
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        reading.setZone(zone);

        if (reading.getRecordedAt() == null) {
            reading.setRecordedAt(new Timestamp(System.currentTimeMillis()));
        }

        return demandReadingRepository.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return demandReadingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        DemandReading reading =
                demandReadingRepository.findFirstByZoneIdOrderByRecordedAtDesc(zoneId);

        if (reading == null) {
            throw new ResourceNotFoundException("not found");
        }
        return reading;
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> all =
                getReadingsForZone(zoneId);

        return all.stream().limit(limit).toList();
    }
}
