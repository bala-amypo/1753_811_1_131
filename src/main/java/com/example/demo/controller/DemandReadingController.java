package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/demand-readings")
@Tag(name = "Demand Readings")
public class DemandReadingController {

    private final DemandReadingService demandReadingService;

    public DemandReadingController(DemandReadingService demandReadingService) {
        this.demandReadingService = demandReadingService;
    }

    @PostMapping
    public DemandReading create(@RequestBody DemandReading reading) {
        return demandReadingService.createReading(reading);
    }

    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getForZone(@PathVariable Long zoneId) {
        return demandReadingService.getReadingsForZone(zoneId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public DemandReading getLatest(@PathVariable Long zoneId) {
        return demandReadingService.getLatestReading(zoneId);
    }

    @GetMapping("/zone/{zoneId}/recent")
    public List<DemandReading> getRecent(
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "5") int limit
    ) {
        return demandReadingService.getRecentReadings(zoneId, limit);
    }
}
