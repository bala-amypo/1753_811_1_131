package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class SupplyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double availableSupplyMW;
    private Timestamp forecastStart;
    private Timestamp forecastEnd;
    private Timestamp generatedAt;

    public SupplyForecast() {}

    public Long getId() { return id; }

    public Double getAvailableSupplyMW() { return availableSupplyMW; }
    public void setAvailableSupplyMW(Double availableSupplyMW) {
        this.availableSupplyMW = availableSupplyMW;
    }

    public Timestamp getForecastStart() { return forecastStart; }
    public void setForecastStart(Timestamp forecastStart) { this.forecastStart = forecastStart; }

    public Timestamp getForecastEnd() { return forecastEnd; }
    public void setForecastEnd(Timestamp forecastEnd) { this.forecastEnd = forecastEnd; }

    public Timestamp getGeneratedAt() { return generatedAt; }

    @PrePersist
    public void onCreate() {
        generatedAt = new Timestamp(System.currentTimeMillis());
    }
}
