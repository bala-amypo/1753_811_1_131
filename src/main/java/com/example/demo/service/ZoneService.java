package com.example.demo.service;

import com.example.demo.entity.Zone;
import java.util.List;

public interface ZoneService {
    Zone createZone(Zone z);
    Zone updateZone(Long id, Zone z);
    Zone getZoneById(Long id);
    List<Zone> getAllZones();
    void deactivateZone(Long id);
}
