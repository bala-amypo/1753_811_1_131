package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import java.util.*;

public interface DemandReadingRepository {
    DemandReading save(DemandReading r);
    List<DemandReading> findByZoneIdOrderByRecordedAtDesc(Long zoneId);
    Optional<DemandReading> findFirstByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
