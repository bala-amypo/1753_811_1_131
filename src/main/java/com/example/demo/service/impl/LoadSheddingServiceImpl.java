package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingEventRepository eventRepository;
    private final ZoneRepository zoneRepository;

    public LoadSheddingServiceImpl(LoadSheddingEventRepository eventRepository,
                                   ZoneRepository zoneRepository) {
        this.eventRepository = eventRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        // simple logic: pick first zone
        Zone zone = zoneRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No zones available"));

        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZone(zone);
        event.setEventStart(Instant.now());

        return eventRepository.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepository.findAll()
                .stream()
                .filter(e -> e.getZone().getId().equals(zoneId))
                .toList();
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}
