package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoadSheddingEvent;

@Repository
public interface LoadSheddingEventRepository
        extends JpaRepository<LoadSheddingEvent, Long> {

    // used to get events for a zone
    List<LoadSheddingEvent> findByZoneIdOrderByEventStartDesc(Long zoneId);
}
