@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;
    private final ZoneRepository zoneRepo;

    public DemandReadingServiceImpl(DemandReadingRepository repo, ZoneRepository zoneRepo) {
        this.repo = repo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public DemandReading createReading(DemandReading r) {
        Long zoneId = r.getZone().getId();

        Zone z = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (r.getDemandMW() < 0)
            throw new BadRequestException(">= 0");

        if (r.getRecordedAt().isAfter(Instant.now()))
            throw new BadRequestException("future");

        r.setZone(z);
        return repo.save(r);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return repo.findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No readings"));
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        return repo.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> all = getReadingsForZone(zoneId);
        return all.subList(0, Math.min(limit, all.size()));
    }
}
