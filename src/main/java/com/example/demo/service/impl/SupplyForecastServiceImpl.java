package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 THIS IS MANDATORY
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repository;

    public SupplyForecastServiceImpl(SupplyForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplyForecast create(SupplyForecast forecast) {
        return repository.save(forecast);
    }

    @Override
    public SupplyForecast getLatest() {
        return repository.findAll().stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException("No forecast found"));
    }

    @Override
    public List<SupplyForecast> getAll() {
        return repository.findAll();
    }
}
