package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository supplyForecastRepository;

    public SupplyForecastServiceImpl(SupplyForecastRepository supplyForecastRepository) {
        this.supplyForecastRepository = supplyForecastRepository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {
        forecast.setGeneratedAt(LocalDateTime.now());
        return supplyForecastRepository.save(forecast);
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return supplyForecastRepository.findById(id).orElse(null);
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return supplyForecastRepository.findAll();
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return supplyForecastRepository
                .findTopByOrderByGeneratedAtDesc()
                .orElse(null);
    }
}
