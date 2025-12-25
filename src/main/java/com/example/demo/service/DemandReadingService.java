package com.example.demo.service;

import com.example.demo.entity.DemandReading;

import java.util.List;

public interface DemandReadingService {

    DemandReading create(DemandReading reading);

    List<DemandReading> getByZone(Long zoneId);

    List<DemandReading> getRecentByZone(Long zoneId);

    DemandReading getLatestByZone(Long zoneId);
}
