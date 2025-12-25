package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SupplyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private double supplyAmount;

    private Instant forecastTime;

    public Long getId() {
        return id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public double getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(double supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    public Instant getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Instant forecastTime) {
        this.forecastTime = forecastTime;
    }
}
