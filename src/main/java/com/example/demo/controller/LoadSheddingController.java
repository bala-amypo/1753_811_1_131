package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.service.LoadSheddingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/load-shedding")
@Tag(name = "Load Shedding")
public class LoadSheddingController {

    private final LoadSheddingService loadSheddingService;

    public LoadSheddingController(LoadSheddingService loadSheddingService) {
        this.loadSheddingService = loadSheddingService;
    }

    @PostMapping("/trigger/{forecastId}")
    public LoadSheddingEvent trigger(@PathVariable Long forecastId) {
        return loadSheddingService.triggerLoadShedding(forecastId);
    }

    @GetMapping("/{id}")
    public LoadSheddingEvent getById(@PathVariable Long id) {
        return loadSheddingService.getEventById(id);
    }

    @GetMapping("/zone/{zoneId}")
    public List<LoadSheddingEvent> getForZone(@PathVariable Long zoneId) {
        return loadSheddingService.getEventsForZone(zoneId);
    }

    @GetMapping
    public List<LoadSheddingEvent> getAll() {
        return loadSheddingService.getAllEvents();
    }
}
