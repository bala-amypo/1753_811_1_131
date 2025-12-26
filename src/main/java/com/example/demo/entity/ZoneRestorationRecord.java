package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "zone_restoration_record")
public class ZoneRestorationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Zone zone;

    private Long eventId;

    private LocalDateTime restoredAt;

    private String notes;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public LocalDateTime getRestoredAt() { return restoredAt; }
    public void setRestoredAt(LocalDateTime restoredAt) { this.restoredAt = restoredAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
