package com.example.demo.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.SupplyForecast;
import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepo;
    private final ZoneRepository zoneRepo;
    private final DemandReadingRepository readingRepo;
    private final LoadSheddingEventRepository eventRepo;

    // ⚠️ ORDER MATTERS – tests instantiate with this constructor
    public LoadSheddingServiceImpl(
            SupplyForecastRepository forecastRepo,
            ZoneRepository zoneRepo,
            DemandReadingRepository readingRepo,
            LoadSheddingEventRepository eventRepo) {
        this.forecastRepo = forecastRepo;
        this.zoneRepo = zoneRepo;
        this.readingRepo = readingRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        List<Zone> activeZones =
                zoneRepo.findByActiveTrueOrderByPriorityLevelAsc();

        if (activeZones.isEmpty()) {
            throw new BadRequestException("No suitable zones");
        }

        double totalDemand = 0;
        List<DemandReading> latestReadings = new ArrayList<>();

        for (Zone zone : activeZones) {
            readingRepo.findFirstByZoneIdOrderByRecordedAtDesc(zone.getId())
                    .ifPresent(r -> {
                        latestReadings.add(r);
                    });
        }

        if (latestReadings.isEmpty()) {
            throw new ResourceNotFoundException("No readings");
        }

        for (DemandReading r : latestReadings) {
            totalDemand += r.getDemandMW();
        }

        if (totalDemand <= forecast.getAvailableSupplyMW()) {
            throw new BadRequestException("No overload");
        }

        // pick lowest priority zone (first in sorted list)
        Zone targetZone = activeZones.get(0);

        DemandReading latest =
                readingRepo.findFirstByZoneIdOrderByRecordedAtDesc(targetZone.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No readings"));

        double reduction = latest.getDemandMW();

        LoadSheddingEvent event = LoadSheddingEvent.builder()
                .zone(targetZone)
                .eventStart(Instant.now())
                .reason("Overload detected")
                .triggeredByForecastId(forecastId)
                .expectedDemandReductionMW(reduction)
                .build();

        return eventRepo.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepo.findByZoneIdOrderByEventStartDesc(zoneId);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepo.findAll();
    }
}
