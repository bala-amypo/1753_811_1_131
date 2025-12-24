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
public class SupplyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Available supply in MW (>= 0)
    private Double availableSupplyMW;

    private Instant forecastStart;
    private Instant forecastEnd;

    // ✅ Auto-set timestamp (IMPORTANT for tests)
    private Instant generatedAt;

    @PrePersist
    public void onCreate() {
        this.generatedAt = Instant.now();
    }
}
