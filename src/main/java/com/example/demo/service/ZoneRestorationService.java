package com.example.demo.service;

import com.example.demo.entity.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneRepositoryService {

    Zone save(Zone zone);

    Optional<Zone> findById(Long id);

    Optional<Zone> findByZoneName(String zoneName);

    List<Zone> findAll();

    List<Zone> findActiveZonesByPriority();

    void deleteById(Long id);
}
