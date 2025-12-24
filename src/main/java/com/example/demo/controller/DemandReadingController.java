package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;

@RestController
@RequestMapping("/api/demand-readings")
public class DemandReadingController {

    private final DemandReadingService demandReadingService;

    // ✅ constructor injection
    public DemandReadingController(DemandReadingService demandReadingService) {
        this.demandReadingService = demandReadingService;
    }

    // ✅ CREATE READING
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DemandReading createReading(@RequestBody DemandReading reading) {
        return demandReadingService.createReading(reading);
    }

    // ✅ GET ALL READINGS FOR ZONE
    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getReadingsForZone(@PathVariable Long zoneId) {
        return demandReadingService.getReadingsForZone(zoneId);
    }

    // ✅ GET LATEST READING
    @GetMapping("/zone/{zoneId}/latest")
    public DemandReading getLatestReading(@PathVariable Long zoneId) {
        return demandReadingService.getLatestReading(zoneId);
    }

    // ✅ GET RECENT N READINGS
    @GetMapping("/zone/{zoneId}/recent")
    public List<DemandReading> getRecentReadings(
            @PathVariable Long zoneId,
            @RequestParam int limit) {
        return demandReadingService.getRecentReadings(zoneId, limit);
    }
}
