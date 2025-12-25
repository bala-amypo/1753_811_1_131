package com.example.demo.controller;


import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/load-shedding")
public class LoadSheddingController {

    private final LoadSheddingService service;

    public LoadSheddingController(LoadSheddingService service) {
        this.service = service;
    }

    // already exists
    @PostMapping("/trigger/{forecastId}")
    public LoadSheddingEvent trigger(@PathVariable Long forecastId) {
        return service.triggerLoadShedding(forecastId);
    }

    // ➕ ADD
    @GetMapping
    public List<LoadSheddingEvent> getAll() {
        return service.getAllEvents();
    }

    // ➕ ADD
    @GetMapping("/{id}")
    public LoadSheddingEvent getById(@PathVariable Long id) {
        return service.getEventById(id);
    }
}
