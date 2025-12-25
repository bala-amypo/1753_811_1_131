package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final LoadSheddingEventRepository eventRepo;
    private final ZoneRestorationRecordRepository recordRepo;

    public ZoneRestorationServiceImpl(
            LoadSheddingEventRepository eventRepo,
            ZoneRestorationRecordRepository recordRepo
    ) {
        this.eventRepo = eventRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public ZoneRestorationRecord restoreZone(Long eventId) {
        LoadSheddingEvent event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getEventStart().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Cannot restore before event start");
        }

        ZoneRestorationRecord record = new ZoneRestorationRecord();
        record.setEvent(event);
        record.setZone(event.getZone());
        record.setRestoredAt(LocalDateTime.now());

        return recordRepo.save(record);
    }
}
