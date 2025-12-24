package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/supply-forecasts")
@Tag(name = "Supply Forecasts")
public class SupplyForecastController {

    private final SupplyForecastService supplyForecastService;

    public SupplyForecastController(SupplyForecastService supplyForecastService) {
        this.supplyForecastService = supplyForecastService;
    }

    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast forecast) {
        return supplyForecastService.createForecast(forecast);
    }

    @PutMapping("/{id}")
    public SupplyForecast update(
            @PathVariable Long id,
            @RequestBody SupplyForecast forecast
    ) {
        return supplyForecastService.updateForecast(id, forecast);
    }

    @GetMapping("/{id}")
    public SupplyForecast getById(@PathVariable Long id) {
        return supplyForecastService.getForecastById(id);
    }

    @GetMapping("/latest")
    public SupplyForecast getLatest() {
        return supplyForecastService.getLatestForecast();
    }

    @GetMapping
    public List<SupplyForecast> getAll() {
        return supplyForecastService.getAllForecasts();
    }
}
