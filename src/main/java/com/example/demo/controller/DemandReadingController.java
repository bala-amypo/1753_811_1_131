package com.example.demo.controller;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand-readings")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    // POST /api/demand-readings
    @PostMapping
    public DemandReading create(@RequestBody DemandReading reading) {
        return service.create(reading);
    }

    // GET /api/demand-readings/zone/{zoneId}
    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }

    // GET /api/demand-readings/zone/{zoneId}/recent
    @GetMapping("/zone/{zoneId}/recent")
    public List<DemandReading> recent(
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "5") int limit) {
        return service.getRecentReadings(zoneId, limit);
    }

    // GET /api/demand-readings/zone/{zoneId}/latest
    @GetMapping("/zone/{zoneId}/latest")
    public DemandReading latest(@PathVariable Long zoneId) {
        return service.getLatestReading(zoneId);
    }
}
