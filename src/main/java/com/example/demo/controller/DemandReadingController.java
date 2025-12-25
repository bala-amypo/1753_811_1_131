package com.example.demo.controller;


import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/demand-readings")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    // already exists
    @GetMappingv("/zone/{zoneId}")
    public List<DemandReading> getByZone(@PathVariable Long zoneId) {
        return service.getReadingsForZone(zoneId);
    }

    // ➕ ADD
    @PostMapping
    public DemandReading create(@RequestBody DemandReading reading) {
        return service.createReading(reading);
    }
}
