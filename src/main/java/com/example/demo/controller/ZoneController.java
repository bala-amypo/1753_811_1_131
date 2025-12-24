package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    // ✅ Constructor injection (tests expect this)
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    // ✅ CREATE ZONE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Zone createZone(@RequestBody Zone zone) {
        return zoneService.createZone(zone);
    }

    // ✅ UPDATE ZONE
    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        return zoneService.updateZone(id, zone);
    }

    // ✅ GET ZONE BY ID
    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id);
    }

    // ✅ GET ALL ZONES
    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    // ✅ DEACTIVATE ZONE
    @PutMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
    }
}
