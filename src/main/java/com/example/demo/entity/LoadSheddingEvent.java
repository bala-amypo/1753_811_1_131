package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private Instant eventStart;
    private Instant eventEnd;

    public Long getId() {
        return id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Instant getEventStart() {
        return eventStart;
    }

    public void setEventStart(Instant eventStart) {
        this.eventStart = eventStart;
    }

    public Instant getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Instant eventEnd) {
        this.eventEnd = eventEnd;
    }
}
