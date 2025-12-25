package com.example.demo.repository;

import com.example.demo.entity.LoadSheddingEvent;
import java.util.*;

public interface LoadSheddingEventRepository {
    LoadSheddingEvent save(LoadSheddingEvent e);
    Optional<LoadSheddingEvent> findById(Long id);
    List<LoadSheddingEvent> findAll();
    List<LoadSheddingEvent> findByZoneIdOrderByEventStartDesc(Long zoneId);
}
