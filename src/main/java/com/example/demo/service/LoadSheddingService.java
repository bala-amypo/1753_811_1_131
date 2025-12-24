package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LoadSheddingEvent;

public interface LoadSheddingService {

    // trigger shedding using forecast
    LoadSheddingEvent triggerLoadShedding(Long forecastId);

    // get event by id
    LoadSheddingEvent getEventById(Long id);

    // get events for zone
    List<LoadSheddingEvent> getEventsForZone(Long zoneId);

    // get all events
    List<LoadSheddingEvent> getAllEvents();
}
