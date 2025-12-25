package com.example.demo.controller;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand-readings")
@Tag(name = "Demand Readings")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    @PostMapping
    public DemandReading create(@RequestBody DemandReading reading) {
        return service.save(reading);
    }

    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> byZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }

    @GetMapping("/zone/{zoneId}/recent")
    public List<DemandReading> recent(@PathVariable Long zoneId) {
        return service.getRecent(zoneId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public DemandReading latest(@PathVariable Long zoneId) {
        return service.getLatest(zoneId);
    }
}
