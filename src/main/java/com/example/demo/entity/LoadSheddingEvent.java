package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "load_shedding_events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private Instant eventStart;

    private Double expectedDemandReductionMW;

    private String reason;
}
