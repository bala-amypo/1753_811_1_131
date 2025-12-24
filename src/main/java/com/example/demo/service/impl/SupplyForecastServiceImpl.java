package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository forecastRepository;

    // ✅ Constructor injection (tests use this)
    public SupplyForecastServiceImpl(SupplyForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {

        // supply must be >= 0
        if (forecast.getAvailableSupplyMW() < 0) {
            throw new BadRequestException("availableSupplyMW must be >= 0");
        }

        // start < end
        if (forecast.getForecastStart().isAfter(forecast.getForecastEnd())) {
            throw new BadRequestException("Invalid time range");
        }

        return forecastRepository.save(forecast);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast forecast) {

        SupplyForecast existing = forecastRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        if (forecast.getAvailableSupplyMW() < 0) {
            throw new BadRequestException("availableSupplyMW must be >= 0");
        }

        if (forecast.getForecastStart().isAfter(forecast.getForecastEnd())) {
            throw new BadRequestException("Invalid time range");
        }

        existing.setAvailableSupplyMW(forecast.getAvailableSupplyMW());
        existing.setForecastStart(forecast.getForecastStart());
        existing.setForecastEnd(forecast.getForecastEnd());

        return forecastRepository.save(existing);
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return forecastRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return forecastRepository.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No forecasts"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return forecastRepository.findAll();
    }
}
