package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;

@RestController
@RequestMapping("/api/supply-forecasts")
public class SupplyForecastController {

    private final SupplyForecastService supplyForecastService;

    // ✅ constructor injection
    public SupplyForecastController(SupplyForecastService supplyForecastService) {
        this.supplyForecastService = supplyForecastService;
    }

    // ✅ CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplyForecast createForecast(@RequestBody SupplyForecast forecast) {
        return supplyForecastService.createForecast(forecast);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public SupplyForecast updateForecast(
            @PathVariable Long id,
            @RequestBody SupplyForecast forecast) {
        return supplyForecastService.updateForecast(id, forecast);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public SupplyForecast getForecastById(@PathVariable Long id) {
        return supplyForecastService.getForecastById(id);
    }

    // ✅ GET LATEST
    @GetMapping("/latest")
    public SupplyForecast getLatestForecast() {
        return supplyForecastService.getLatestForecast();
    }

    // ✅ GET ALL
    @GetMapping
    public List<SupplyForecast> getAllForecasts() {
        return supplyForecastService.getAllForecasts();
    }
}
