package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repo;

    public SupplyForecastServiceImpl(SupplyForecastRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return repo.findAll();
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {
        return repo.save(forecast);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast forecast) {
        forecast.setId(id);
        return repo.save(forecast);
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return repo.findTopByOrderByForecastTimeDesc();
    }
}
