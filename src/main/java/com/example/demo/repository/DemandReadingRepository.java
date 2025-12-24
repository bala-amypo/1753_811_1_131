package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemandReading;

@Repository
public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    // ✅ Get latest reading for a zone
    Optional<DemandReading> findFirstByZoneIdOrderByRecordedAtDesc(Long zoneId);

    // ✅ Get all readings for a zone (latest first)
    List<DemandReading> findByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
