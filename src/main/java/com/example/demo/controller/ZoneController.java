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

    // already exists (KEEP)
    @GetMapping
    public List<Zone> getAll() {
        return service.getAllZones();
    }

    // ➕ ADD
    @GetMapping("/{id}")
    public Zone getById(@PathVariable Long id) {
        return service.getZone(id);
    }

    // ➕ ADD
    @PostMapping
    public Zone create(@RequestBody Zone zone) {
        return service.createZone(zone);
    }

    // ➕ ADD
    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id, @RequestBody Zone zone) {
        return service.updateZone(id, zone);
    }

    // ➕ ADD (soft delete)
    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateZone(id);
    }
}
