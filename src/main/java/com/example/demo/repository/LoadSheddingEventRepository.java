package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface LoadSheddingEventRepository extends JpaRepository<LoadSheddingEvent, Long> {
    List<LoadSheddingEvent> findByZoneIdOrderByEventStartDesc(Long zoneId);
}
