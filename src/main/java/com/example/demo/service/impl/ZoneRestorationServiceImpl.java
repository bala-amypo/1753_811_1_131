package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository restorationRepo;
    private final LoadSheddingEventRepository eventRepo;
    private final ZoneRepository zoneRepo;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository restorationRepo,
            LoadSheddingEventRepository eventRepo,
            ZoneRepository zoneRepo
    ) {
        this.restorationRepo = restorationRepo;
        this.eventRepo = eventRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord record) {

        Zone zone = zoneRepo.findById(record.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        LoadSheddingEvent event = eventRepo.findById(record.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (record.getRestoredAt().before(event.getEventStart())) {
        throw new BadRequestException("after event start");
}


        return restorationRepo.save(record);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return restorationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return restorationRepo.findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
