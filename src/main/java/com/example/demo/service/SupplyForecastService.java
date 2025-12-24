package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SupplyForecast;

public interface SupplyForecastService {

    // ✅ Create forecast
    SupplyForecast createForecast(SupplyForecast forecast);

    // ✅ Update forecast
    SupplyForecast updateForecast(Long id, SupplyForecast forecast);

    // ✅ Get forecast by id
    SupplyForecast getForecastById(Long id);

    // ✅ Get latest forecast
    SupplyForecast getLatestForecast();

    // ✅ Get all forecasts
    List<SupplyForecast> getAllForecasts();
}
