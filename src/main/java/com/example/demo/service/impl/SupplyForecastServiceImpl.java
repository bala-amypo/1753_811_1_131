package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.exception.*;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;

import java.util.List;

public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repo;

    public SupplyForecastServiceImpl(SupplyForecastRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast f) {
        if (f.getAvailableSupplyMW() < 0)
            throw new BadRequestException(">= 0");

        if (!f.getForecastStart().isBefore(f.getForecastEnd()))
            throw new BadRequestException("range");

        return repo.save(f);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast f) {
        SupplyForecast existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        if (f.getAvailableSupplyMW() < 0)
            throw new BadRequestException(">= 0");

        if (!f.getForecastStart().isBefore(f.getForecastEnd()))
            throw new BadRequestException("range");

        existing.setAvailableSupplyMW(f.getAvailableSupplyMW());
        existing.setForecastStart(f.getForecastStart());
        existing.setForecastEnd(f.getForecastEnd());

        return repo.save(existing);
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return repo.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No forecasts"));
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return repo.findAll();
    }
}
