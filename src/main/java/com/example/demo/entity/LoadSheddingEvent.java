package com.example.demo.entity;

import lombok.*;
import java.time.Instant;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class LoadSheddingEvent {
    private Long id;
    private Zone zone;
    private Instant eventStart;
    private Double expectedDemandReductionMW;
    private String reason;
}
