package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DemandReadingRepository
        extends JpaRepository<DemandReading, Long> {

    // All readings for a zone
    List<DemandReading> findByZoneId(Long zoneId);

    // Recent N readings
    List<DemandReading> findByZoneIdOrderByTimestampDesc(
            Long zoneId,
            Pageable pageable
    );

    // Latest single reading
    DemandReading findTopByZoneIdOrderByTimestampDesc(Long zoneId);
}
