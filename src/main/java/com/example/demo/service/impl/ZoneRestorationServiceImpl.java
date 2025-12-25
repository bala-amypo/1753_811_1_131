@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository repo;
    private final LoadSheddingEventRepository eventRepo;
    private final ZoneRepository zoneRepo;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository repo,
            LoadSheddingEventRepository eventRepo,
            ZoneRepository zoneRepo) {

        this.repo = repo;
        this.eventRepo = eventRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord r) {
        LoadSheddingEvent ev = eventRepo.findById(r.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Zone z = zoneRepo.findById(r.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (r.getRestoredAt().isBefore(ev.getEventStart()))
            throw new BadRequestException("after event start");

        r.setZone(z);
        return repo.save(r);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return repo.findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
