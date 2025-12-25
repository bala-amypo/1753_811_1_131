package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;
public interface SupplyForecastRepository extends JpaRepository<SupplyForecast, Long> {
    Optional<SupplyForecast> findFirstByOrderByGeneratedAtDesc();
}
