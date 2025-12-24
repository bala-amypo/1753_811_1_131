package com.example.demo.entity;

import java.time.Instant;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Each reading belongs to one Zone
    @ManyToOne(optional = false)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    // ✅ Demand in MW (must be >= 0)
    private Double demandMW;

    // ✅ Time when reading was recorded
    private Instant recordedAt;
}
