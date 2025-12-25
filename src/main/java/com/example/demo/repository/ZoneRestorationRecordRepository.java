package com.example.demo.repository;

import com.example.demo.entity.ZoneRestorationRecord;
import java.util.*;

public interface ZoneRestorationRecordRepository {
    ZoneRestorationRecord save(ZoneRestorationRecord r);
    Optional<ZoneRestorationRecord> findById(Long id);
    List<ZoneRestorationRecord> findByZoneIdOrderByRestoredAtDesc(Long zoneId);
}
