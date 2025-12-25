package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.LoadSheddingService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepo;
    private final LoadSheddingEventRepository eventRepo;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository forecastRepo,
            LoadSheddingEventRepository eventRepo) {
        this.forecastRepo = forecastRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {
        SupplyForecast forecast =
                forecastRepo.findById(forecastId).orElse(null);

        if (forecast == null) return null;

        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setForecast(forecast);
        event.setTriggeredAt(Instant.now());
        event.setStatus("TRIGGERED");

        return eventRepo.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepo.findById(id).orElse(null);
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepo.findByForecastZoneId(zoneId);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepo.findAll();
    }
}
