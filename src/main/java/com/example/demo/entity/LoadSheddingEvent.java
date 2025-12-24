package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private Timestamp eventStart;
    private Timestamp eventEnd;
    private String reason;
    private Long triggeredByForecastId;
    private Double expectedDemandReductionMW;

    public LoadSheddingEvent() {}

    public Long getId() { return id; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public Timestamp getEventStart() { return eventStart; }
    public void setEventStart(Timestamp eventStart) { this.eventStart = eventStart; }

    public Timestamp getEventEnd() { return eventEnd; }
    public void setEventEnd(Timestamp eventEnd) { this.eventEnd = eventEnd; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Long getTriggeredByForecastId() { return triggeredByForecastId; }
    public void setTriggeredByForecastId(Long triggeredByForecastId) {
        this.triggeredByForecastId = triggeredByForecastId;
    }

    public Double getExpectedDemandReductionMW() {
        return expectedDemandReductionMW;
    }

    public void setExpectedDemandReductionMW(Double expectedDemandReductionMW) {
        this.expectedDemandReductionMW = expectedDemandReductionMW;
    }
}
