package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ZoneRestorationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private Timestamp restoredAt;
    private Long eventId;
    private String notes;

    public ZoneRestorationRecord() {}

    public Long getId() { return id; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public Timestamp getRestoredAt() { return restoredAt; }
    public void setRestoredAt(Timestamp restoredAt) { this.restoredAt = restoredAt; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
