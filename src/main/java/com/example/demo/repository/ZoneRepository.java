package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Optional<Zone> findByZoneName(String zoneName);
    List<Zone> findByActiveTrueOrderByPriorityLevelAsc();
}
