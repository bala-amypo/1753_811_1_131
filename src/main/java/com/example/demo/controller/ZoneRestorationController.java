package com.example.demo.controller;


import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService service;

    public ZoneRestorationController(ZoneRestorationService service) {
        this.service = service;
    }

    // already exists
    @PostMapping("/restore/{eventId}")
    public ZoneRestorationRecord restore(@PathVariable Long eventId) {
        return service.restoreZone(eventId);
    }

    // ➕ ADD
    @GetMapping
    public List<ZoneRestorationRecord> getAll() {
        return service.getAll();
    }
}
