package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandReadingRepository
        extends JpaRepository<DemandReading, Long> {

    // all readings of a zone
    List<DemandReading> findByZoneId(Long zoneId);

    // ordered readings (used for recent + latest)
    List<DemandReading> findByZoneIdOrderByRecordedAtDesc(
            Long zoneId, Pageable pageable
    );
}
