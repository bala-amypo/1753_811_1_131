package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SupplyForecast forecast;

    private Instant triggeredAt;

    private String status;

    public Long getId() {
        return id;
    }

    public SupplyForecast getForecast() {
        return forecast;
    }

    public void setForecast(SupplyForecast forecast) {
        this.forecast = forecast;
    }

    public Instant getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(Instant triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
