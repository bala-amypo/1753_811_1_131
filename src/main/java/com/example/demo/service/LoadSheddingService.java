package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LoadSheddingEvent;

public interface LoadSheddingService {

    LoadSheddingEvent triggerLoadShedding(Long forecastId);

    LoadSheddingEvent getEventById(Long id);

    List<LoadSheddingEvent> getEventsForZone(Long zoneId);

    List<LoadSheddingEvent> getAllEvents();
}
