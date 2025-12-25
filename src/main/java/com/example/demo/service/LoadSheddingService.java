package com.example.demo.service;

import com.example.demo.entity.LoadSheddingEvent;
import java.util.List;

public interface LoadSheddingService {

    LoadSheddingEvent triggerLoadShedding(Long forecastId);

    List<LoadSheddingEvent> getAllEvents();

    LoadSheddingEvent getEventById(Long id);

    List<LoadSheddingEvent> getEventsForZone(Long zoneId);
}
