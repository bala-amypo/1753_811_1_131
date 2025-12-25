package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import java.util.*;

public interface DemandReadingRepository {

    DemandReading save(DemandReading reading);

    List<DemandReading> findByZoneId(Long zoneId);

    Optional<DemandReading> findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
