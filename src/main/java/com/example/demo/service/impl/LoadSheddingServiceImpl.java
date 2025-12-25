package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository supplyForecastRepository;
    private final ZoneRepository zoneRepository;
    private final DemandReadingRepository demandReadingRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository supplyForecastRepository,
            ZoneRepository zoneRepository,
            DemandReadingRepository demandReadingRepository,
            LoadSheddingEventRepository loadSheddingEventRepository) {

        this.supplyForecastRepository = supplyForecastRepository;
        this.zoneRepository = zoneRepository;
        this.demandReadingRepository = demandReadingRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast =
                supplyForecastRepository.findById(forecastId).orElse(null);
        if (forecast == null) return null;

        Zone zone = zoneRepository.findById(forecast.getZone().getId()).orElse(null);
        if (zone == null) return null;

        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZone(zone);
        event.setForecast(forecast);
        event.setTriggeredAt(LocalDateTime.now());
        event.setStatus("TRIGGERED");

        return loadSheddingEventRepository.save(event);
    }

    @Override
    public LoadSheddingEvent getById(Long id) {
        return loadSheddingEventRepository.findById(id).orElse(null);
    }

    @Override
    public List<LoadSheddingEvent> getAll() {
        return loadSheddingEventRepository.findAll();
    }

    @Override
    public List<LoadSheddingEvent> getByZone(Long zoneId) {
        return loadSheddingEventRepository.findByZoneId(zoneId);
    }
}
