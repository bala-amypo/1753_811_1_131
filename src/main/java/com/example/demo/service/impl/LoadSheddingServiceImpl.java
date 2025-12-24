package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.SupplyForecast;
import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository supplyForecastRepository;
    private final ZoneRepository zoneRepository;
    private final DemandReadingRepository demandReadingRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository supplyForecastRepository,
            ZoneRepository zoneRepository,
            DemandReadingRepository demandReadingRepository,
            LoadSheddingEventRepository loadSheddingEventRepository
    ) {
        this.supplyForecastRepository = supplyForecastRepository;
        this.zoneRepository = zoneRepository;
        this.demandReadingRepository = demandReadingRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = supplyForecastRepository.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        double totalDemand = 0;
        List<Zone> activeZones =
                zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();

        for (Zone zone : activeZones) {
            DemandReading latest =
                    demandReadingRepository
                            .findFirstByZoneIdOrderByRecordedAtDesc(zone.getId());
            if (latest != null) {
                totalDemand += latest.getDemandMW();
            }
        }

        if (totalDemand <= forecast.getAvailableSupplyMW()) {
            throw new BadRequestException("No overload");
        }

        for (Zone zone : activeZones) {

            DemandReading latest =
                    demandReadingRepository
                            .findFirstByZoneIdOrderByRecordedAtDesc(zone.getId());

            if (latest == null) continue;

            double zoneDemand = latest.getDemandMW();

            LoadSheddingEvent event = new LoadSheddingEvent();
            event.setZone(zone);
            event.setEventStart(new Timestamp(System.currentTimeMillis()));
            event.setReason("Overload");
            event.setTriggeredByForecastId(forecastId);
            event.setExpectedDemandReductionMW(zoneDemand);

            loadSheddingEventRepository.save(event);

            totalDemand -= zoneDemand;

            if (totalDemand <= forecast.getAvailableSupplyMW()) {
                return event; 
            }
        }

        return null;
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return loadSheddingEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return loadSheddingEventRepository
                .findByZoneIdOrderByEventStartDesc(zoneId);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return loadSheddingEventRepository.findAll();
    }
}
