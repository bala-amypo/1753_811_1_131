package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.service.ZoneRestorationService;

@RestController
@RequestMapping("/api/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService service;

    public ZoneRestorationController(ZoneRestorationService service) {
        this.service = service;
    }

    @PostMapping
    public ZoneRestorationRecord restore(@RequestBody ZoneRestorationRecord record) {
        return service.restoreZone(record);
    }

    @GetMapping("/{id}")
    public ZoneRestorationRecord getById(@PathVariable Long id) {
        return service.getRecordById(id);
    }

    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> byZone(@PathVariable Long zoneId) {
        return service.getRecordsForZone(zoneId);
    }
}
