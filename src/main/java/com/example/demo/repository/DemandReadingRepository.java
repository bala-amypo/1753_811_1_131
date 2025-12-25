package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {
    Optional<DemandReading> findFirstByZoneIdOrderByRecordedAtDesc(Long zoneId);
    List<DemandReading> findByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
