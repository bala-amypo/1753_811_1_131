package com.example.demo.service;

import com.example.demo.entity.SupplyForecast;
import java.util.List;
public interface SupplyForecastService {
    List<SupplyForecast> getAllForecasts();
    SupplyForecast getForecastById(Long id);
    SupplyForecast createForecast(SupplyForecast forecast);
    SupplyForecast updateForecast(Long id, SupplyForecast forecast);
    SupplyForecast getLatestForecast();
}
