package com.example.demo.repository;

import com.example.demo.entity.SupplyForecast;
import java.util.*;

public interface SupplyForecastRepository {
    SupplyForecast save(SupplyForecast f);
    Optional<SupplyForecast> findById(Long id);
    Optional<SupplyForecast> findFirstByOrderByGeneratedAtDesc();
    List<SupplyForecast> findAll();
}
