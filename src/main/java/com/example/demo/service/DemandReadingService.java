package com.example.demo.service;

import com.example.demo.entity.DemandReading;
import java.util.List;

public interface DemandReadingService {

    List<DemandReading> getByZone(Long zoneId);

    DemandReading create(DemandReading reading);

    List<DemandReading> getRecentReadings(Long zoneId, int limit);
}
