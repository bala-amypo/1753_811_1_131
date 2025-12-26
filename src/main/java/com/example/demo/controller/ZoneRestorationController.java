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

    // STEP 7 – Restore Zone
    @PostMapping
    public ZoneRestorationRecord restore(@RequestBody ZoneRestorationRecord record) {
        return service.restoreZone(record);
    }

    @GetMapping("/{id}")
    public ZoneRestorationRecord get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> byZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }
}
