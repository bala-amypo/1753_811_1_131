package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemandReadingRepository
        extends JpaRepository<DemandReading, Long> {

    List<DemandReading> findByZoneId(Long zoneId);

    List<DemandReading> findTop5ByZoneIdOrderByRecordedAtDesc(Long zoneId);

    Optional<DemandReading> findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
