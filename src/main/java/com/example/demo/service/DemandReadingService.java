package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DemandReading;

public interface DemandReadingService {

    DemandReading createReading(DemandReading reading);

    List<DemandReading> getReadingsForZone(Long zoneId);

    DemandReading getLatestReading(Long zoneId);

    List<DemandReading> getRecentReadings(Long zoneId, int limit);
}
