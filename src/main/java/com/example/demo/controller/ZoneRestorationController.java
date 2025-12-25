package com.example.demo.controller;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService service;

    public ZoneRestorationController(ZoneRestorationService service) {
        this.service = service;
    }

    // POST /api/restorations
    @PostMapping
    public ZoneRestorationRecord restore(@RequestParam Long eventId) {
        return service.restoreZone(eventId);
    }

    // GET /api/restorations/{id}
    @GetMapping("/{id}")
    public ZoneRestorationRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET /api/restorations/zone/{zoneId}
    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }
}
