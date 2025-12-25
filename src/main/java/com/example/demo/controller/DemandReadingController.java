package com.example.demo.controller;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    // create reading
    @PostMapping
    public DemandReading create(@RequestBody DemandReading reading) {
        return service.create(reading);
    }

    // all readings of a zone
    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }

    // recent readings (last 5 – fixed in service)
    @GetMapping("/zone/{zoneId}/recent")
    public List<DemandReading> getRecent(@PathVariable Long zoneId) {
        return service.getRecentByZone(zoneId);
    }

    // latest reading
    @GetMapping("/zone/{zoneId}/latest")
    public DemandReading getLatest(@PathVariable Long zoneId) {
        return service.getLatestByZone(zoneId);
    }
}
