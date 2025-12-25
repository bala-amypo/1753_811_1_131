package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository restorationRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;
    private final ZoneRepository zoneRepository;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository restorationRepository,
            LoadSheddingEventRepository loadSheddingEventRepository,
            ZoneRepository zoneRepository) {

        this.restorationRepository = restorationRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ZoneRestorationRecord restoreZone(Long zoneId) {

        Zone zone = zoneRepository.findById(zoneId).orElse(null);
        if (zone == null) return null;

        ZoneRestorationRecord record = new ZoneRestorationRecord();
        record.setZone(zone);
        record.setRestoredAt(LocalDateTime.now());
        record.setStatus("RESTORED");

        return restorationRepository.save(record);
    }

    @Override
    public ZoneRestorationRecord getById(Long id) {
        return restorationRepository.findById(id).orElse(null);
    }

    @Override
    public List<ZoneRestorationRecord> getByZone(Long zoneId) {
        return restorationRepository.findByZoneId(zoneId);
    }
}
