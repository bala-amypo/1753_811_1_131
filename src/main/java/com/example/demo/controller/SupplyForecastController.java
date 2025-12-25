package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supply-forecasts")
public class SupplyForecastController {
}@RestController
@RequestMapping("/forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    @GetMapping("/latest")
    public SupplyForecast getLatest() {
        return service.getLatestForecast();
    }
}
