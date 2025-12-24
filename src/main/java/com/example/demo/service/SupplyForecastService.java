package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SupplyForecast;

public interface SupplyForecastService {

    SupplyForecast createForecast(SupplyForecast forecast);

    SupplyForecast updateForecast(Long id, SupplyForecast forecast);

    SupplyForecast getForecastById(Long id);

    SupplyForecast getLatestForecast();

    List<SupplyForecast> getAllForecasts();
}
