package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast f) {
        return service.create(f);
    }

    @GetMapping("/latest")
    public SupplyForecast latest() {
        return service.getLatest();
    }
}
