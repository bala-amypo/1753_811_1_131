package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService service;

    public ZoneController(ZoneService service) {
        this.service = service;
    }

    // GET /api/zones
    @GetMapping
    public List<Zone> getAllZones() {
        return service.getAllZones();
    }

    // GET /api/zones/{id}
    @GetMapping("/{id}")
    public Zone getZone(@PathVariable Long id) {
        return service.getZoneById(id);
    }

    // POST /api/zones
    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return service.createZone(zone);
    }

    // PUT /api/zones/{id}
    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        return service.updateZone(id, zone);
    }

    // PUT /api/zones/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivateZone(@PathVariable Long id) {
        service.deactivateZone(id);
    }
}
