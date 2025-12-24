package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoadSheddingEvent;

public interface LoadSheddingEventRepository
        extends JpaRepository<LoadSheddingEvent, Long> {

    List<LoadSheddingEvent> findByZoneIdOrderByEventStartDesc(Long zoneId);
}
