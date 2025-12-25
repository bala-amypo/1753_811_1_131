package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface ZoneRestorationRecordRepository extends JpaRepository<ZoneRestorationRecord, Long> {
    List<ZoneRestorationRecord> findByZoneIdOrderByRestoredAtDesc(Long zoneId);
}
