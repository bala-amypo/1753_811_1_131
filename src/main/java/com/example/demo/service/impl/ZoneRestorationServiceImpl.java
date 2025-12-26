package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.ZoneRestorationRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRepository restorationRepo;
    private final ZoneRepository zoneRepo;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRepository restorationRepo,
            ZoneRepository zoneRepo) {
        this.restorationRepo = restorationRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord record) {

        Zone zone = zoneRepo.findById(record.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zone.setActive(true);
        zoneRepo.save(zone);

        record.setZone(zone);
        record.setRestoredAt(
                record.getRestoredAt() != null
                        ? record.getRestoredAt()
                        : LocalDateTime.now()
        );

        return restorationRepo.save(record);
    }

    @Override
    public ZoneRestorationRecord getById(Long id) {
        return restorationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getByZone(Long zoneId) {
        return restorationRepo.findByZoneId(zoneId);
    }
}
