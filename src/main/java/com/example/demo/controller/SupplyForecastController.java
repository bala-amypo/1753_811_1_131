package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supply-forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    // GET /api/supply-forecasts
    @GetMapping
    public List<SupplyForecast> getAll() {
        return service.getAllForecasts();
    }

    // GET /api/supply-forecasts/{id}
    @GetMapping("/{id}")
    public SupplyForecast getById(@PathVariable Long id) {
        return service.getForecastById(id);
    }

    // POST /api/supply-forecasts
    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast forecast) {
        return service.createForecast(forecast);
    }

    // PUT /api/supply-forecasts/{id}
    @PutMapping("/{id}")
    public SupplyForecast update(
            @PathVariable Long id,
            @RequestBody SupplyForecast forecast) {
        return service.updateForecast(id, forecast);
    }

    // GET /api/supply-forecasts/latest
    @GetMapping("/latest")
    public SupplyForecast latest() {
        return service.getLatestForecast();
    }
}
