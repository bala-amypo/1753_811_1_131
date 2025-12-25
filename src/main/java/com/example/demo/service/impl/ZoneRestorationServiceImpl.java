package com.example.demo.service.impl;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository repo;

    public ZoneRestorationServiceImpl(ZoneRestorationRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public ZoneRestorationRecord create(ZoneRestorationRecord record) {
        record.setRestoredAt(Instant.now());
        return repo.save(record);
    }

    @Override
    public ZoneRestorationRecord getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<ZoneRestorationRecord> getByZone(Long zoneId) {
        return repo.findByZoneId(zoneId);
    }
}
