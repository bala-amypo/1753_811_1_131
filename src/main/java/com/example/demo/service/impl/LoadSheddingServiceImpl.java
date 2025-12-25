package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.SupplyForecast;
import com.example.demo.entity.Zone;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingEventRepository eventRepository;
    private final SupplyForecastRepository forecastRepository;
    private final ZoneRepository zoneRepository;

    public LoadSheddingServiceImpl(
            LoadSheddingEventRepository eventRepository,
            SupplyForecastRepository forecastRepository,
            ZoneRepository zoneRepository) {
        this.eventRepository = eventRepository;
        this.forecastRepository = forecastRepository;
        this.zoneRepository = zoneRepository;
    }

    // 1️⃣ Trigger load shedding
    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = forecastRepository.findById(forecastId)
                .orElseThrow(() -> new RuntimeException("Forecast not found"));

        List<Zone> activeZones = zoneRepository.findByActiveTrue();
        if (activeZones.isEmpty()) {
            throw new RuntimeException("No active zones available");
        }

        Zone selectedZone = activeZones.stream()
                .min(Comparator.comparingInt(Zone::getPriorityLevel))
                .orElseThrow();

        LoadSheddingEvent event = LoadSheddingEvent.builder()
                .zone(selectedZone)
                .eventStart(Instant.now())
                .reason("Demand exceeded supply")
                .build();

        selectedZone.setActive(false);
        zoneRepository.save(selectedZone);

        return eventRepository.save(event);
    }

    // 2️⃣ Get event by ID
    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // 3️⃣ Get events for a zone
    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepository.findByZoneId(zoneId);
    }

    // 4️⃣ Get all events
    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}
