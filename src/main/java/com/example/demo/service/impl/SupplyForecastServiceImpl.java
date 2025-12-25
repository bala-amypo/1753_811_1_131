package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repo;

    @Override
    public SupplyForecast createForecast(SupplyForecast f) {
        if (f.getAvailableSupplyMW() < 0)
            throw new BadRequestException("Supply must be >= 0");

        if (f.getForecastStart().isAfter(f.getForecastEnd()))
            throw new BadRequestException("Invalid time range");

        return repo.save(f);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast updated) {
        SupplyForecast f = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        f.setAvailableSupplyMW(updated.getAvailableSupplyMW());
        f.setForecastStart(updated.getForecastStart());
        f.setForecastEnd(updated.getForecastEnd());

        return repo.save(f);
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return repo.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No forecasts"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return repo.findAll();
    }
}
    