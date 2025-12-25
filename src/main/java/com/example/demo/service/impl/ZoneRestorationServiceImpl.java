package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.ZoneRestorationService;

import java.util.List;

public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository restorationRepo;
    private final LoadSheddingEventRepository eventRepo;
    private final ZoneRepository zoneRepo;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository restorationRepo,
            LoadSheddingEventRepository eventRepo,
            ZoneRepository zoneRepo) {
        this.restorationRepo = restorationRepo;
        this.eventRepo = eventRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord record) {

        LoadSheddingEvent event = eventRepo.findById(record.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Zone zone = zoneRepo.findById(record.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!record.getRestoredAt().isAfter(event.getEventStart()))
            throw new BadRequestException("after event start");

        record.setZone(zone);
        return restorationRepo.save(record);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return restorationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return restorationRepo.findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
