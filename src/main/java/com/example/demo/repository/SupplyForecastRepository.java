package com.example.demo.repository;

import com.example.demo.entity.SupplyForecast;
import java.util.*;

public interface SupplyForecastRepository {

    SupplyForecast save(SupplyForecast forecast);

    Optional<SupplyForecast> findById(Long id);

    List<SupplyForecast> findAll();

    Optional<SupplyForecast> findTopByOrderByGeneratedAtDesc();
}
