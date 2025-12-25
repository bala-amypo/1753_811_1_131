package com.example.demo.service;

import com.example.demo.entity.LoadSheddingEvent;
import java.util.List;

public interface LoadSheddingService {

    LoadSheddingEvent triggerLoadShedding(Long forecastId);

    LoadSheddingEvent getById(Long id);

    List<LoadSheddingEvent> getAll();

    List<LoadSheddingEvent> getByZone(Long zoneId);
}
