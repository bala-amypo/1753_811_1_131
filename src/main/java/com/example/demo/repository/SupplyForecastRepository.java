package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SupplyForecast;

@Repository
public interface SupplyForecastRepository
        extends JpaRepository<SupplyForecast, Long> {

    // ✅ Get latest forecast (tests depend on this exact name)
    Optional<SupplyForecast> findFirstByOrderByGeneratedAtDesc();
}
