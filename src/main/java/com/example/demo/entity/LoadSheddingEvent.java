package com.example.demo.entity;

import java.time.Instant;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // each event belongs to a zone
    @ManyToOne(optional = false)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private Instant eventStart;

    private Instant eventEnd;

    private String reason;

    private Long triggeredByForecastId;

    // must be >= 0
    private Double expectedDemandReductionMW;
}
