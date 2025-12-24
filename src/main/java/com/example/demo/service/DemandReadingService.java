package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DemandReading;

public interface DemandReadingService {

    // ✅ Create a new demand reading
    DemandReading createReading(DemandReading reading);

    // ✅ Get all readings for a zone
    List<DemandReading> getReadingsForZone(Long zoneId);

    // ✅ Get latest reading for a zone
    DemandReading getLatestReading(Long zoneId);

    // ✅ Get last N readings
    List<DemandReading> getRecentReadings(Long zoneId, int limit);
}
