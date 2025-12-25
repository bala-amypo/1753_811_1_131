@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepo;
    private final ZoneRepository zoneRepo;
    private final DemandReadingRepository readingRepo;
    private final LoadSheddingEventRepository eventRepo;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository forecastRepo,
            ZoneRepository zoneRepo,
            DemandReadingRepository readingRepo,
            LoadSheddingEventRepository eventRepo) {

        this.forecastRepo = forecastRepo;
        this.zoneRepo = zoneRepo;
        this.readingRepo = readingRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {
        SupplyForecast f = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        List<Zone> zones = zoneRepo.findByActiveTrueOrderByPriorityLevelAsc();
        if (zones.isEmpty())
            throw new BadRequestException("No overload");

        double totalDemand = 0;
        for (Zone z : zones) {
            Optional<DemandReading> r =
                    readingRepo.findFirstByZoneIdOrderByRecordedAtDesc(z.getId());
            if (r.isPresent())
                totalDemand += r.get().getDemandMW();
        }

        if (totalDemand <= f.getAvailableSupplyMW())
            throw new BadRequestException("No overload");

        Zone cut = zones.get(0);
        DemandReading dr = readingRepo
                .findFirstByZoneIdOrderByRecordedAtDesc(cut.getId())
                .orElseThrow(() -> new BadRequestException("No suitable"));

        LoadSheddingEvent e = LoadSheddingEvent.builder()
                .zone(cut)
                .eventStart(Instant.now())
                .expectedDemandReductionMW(dr.getDemandMW())
                .reason("Overload")
                .build();

        return eventRepo.save(e);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepo.findByZoneIdOrderByEventStartDesc(zoneId);
    }
}
