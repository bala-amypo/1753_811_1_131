package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ZoneRestorationRecord;

@Repository
public interface ZoneRestorationRecordRepository
        extends JpaRepository<ZoneRestorationRecord, Long> {

    // used for restoration history
    List<ZoneRestorationRecord> findByZoneIdOrderByRestoredAtDesc(Long zoneId);
}
