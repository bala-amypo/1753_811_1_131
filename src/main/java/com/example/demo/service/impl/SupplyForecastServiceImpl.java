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

    private final SupplyForecastRepository supplyForecastRepository;

    public SupplyForecastServiceImpl(SupplyForecastRepository supplyForecastRepository) {
        this.supplyForecastRepository = supplyForecastRepository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {

        if (forecast.getAvailableSupplyMW() == null ||
            forecast.getAvailableSupplyMW() < 0) {
            throw new BadRequestException(">=0");
        }

        return supplyForecastRepository.save(forecast);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast forecast) {

        SupplyForecast existing = supplyForecastRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (forecast.getAvailableSupplyMW() != null &&
            forecast.getAvailableSupplyMW() < 0) {
            throw new BadRequestException(">=0");
        }

        existing.setAvailableSupplyMW(forecast.getAvailableSupplyMW());
        existing.setForecastStart(forecast.getForecastStart());
        existing.setForecastEnd(forecast.getForecastEnd());

        return supplyForecastRepository.save(existing);
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return supplyForecastRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public SupplyForecast getLatestForecast() {
        SupplyForecast forecast =
                supplyForecastRepository.findFirstByOrderByGeneratedAtDesc();

        if (forecast == null) {
            throw new ResourceNotFoundException("not found");
        }
        return forecast;
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return supplyForecastRepository.findAll();
    }
}
