package com.example.demo.service.impl;
import java.util.List;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service   // 🔥 THIS IS THE KEY
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final ZoneRepository zoneRepo;
    private final SupplyForecastRepository forecastRepo;
    private final LoadSheddingEventRepository eventRepo;

    public LoadSheddingServiceImpl(
            ZoneRepository zoneRepo,
            SupplyForecastRepository forecastRepo,
            LoadSheddingEventRepository eventRepo
    ) {
        this.zoneRepo = zoneRepo;
        this.forecastRepo = forecastRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        var forecast = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new RuntimeException("Forecast not found"));

        Zone zone = zoneRepo.findAll().stream()
                .filter(Zone::getActive)
                .min((a, b) -> Integer.compare(a.getPriorityLevel(), b.getPriorityLevel()))
                .orElseThrow(() -> new RuntimeException("No zone available"));

        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZone(zone);
        event.setEventStart(Instant.now());
        event.setEventEnd(Instant.now().plusSeconds(3600));

        return eventRepo.save(event);
    }
}
