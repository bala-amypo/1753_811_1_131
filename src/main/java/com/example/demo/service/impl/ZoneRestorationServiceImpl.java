package com.example.demo.service.impl;
import java.util.List; 
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;


import java.time.Instant;

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
public List<ZoneRestorationRecord> getAll() {
    return repo.findAll();
}


    @Override
    public ZoneRestorationRecord restoreZone(Long eventId) {

        LoadSheddingEvent event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Test expects Instant comparison
        if (event.getEventStart().isAfter(Instant.now())) {
            throw new RuntimeException("Cannot restore before event start");
        }

        ZoneRestorationRecord record = new ZoneRestorationRecord();
        record.setEventId(event.getId());      // ✅ correct
        record.setZone(event.getZone());       // ✅ correct
        record.setRestoredAt(Instant.now());   // ✅ Instant (not LocalDateTime)

        return recordRepo.save(record);
    }
}
