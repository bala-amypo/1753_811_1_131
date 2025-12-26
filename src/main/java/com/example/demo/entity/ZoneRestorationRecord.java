package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "zone_restorations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoneRestorationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zone zone;

    private Long eventId;

    private Instant restoredAt;

    private String notes;
}
