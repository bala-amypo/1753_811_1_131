package com.example.demo.service;

import com.example.demo.entity.DemandReading;
import java.util.List;

public interface DemandReadingService {
    DemandReading create(DemandReading reading);
    List<DemandReading> getByZone(Long zoneId);
    List<DemandReading> getRecentReadings(Long zoneId, int limit);
    DemandReading getLatestReading(Long zoneId);
}
