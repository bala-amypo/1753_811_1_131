package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zone_restoration_record")
public class ZoneRestorationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private Long eventId;

    private Instant restoredAt;

    private String notes;
}
