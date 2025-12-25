@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public Zone createZone(Zone z) {
        if (z.getPriorityLevel() < 1)
            throw new BadRequestException("priority >= 1");

        repo.findByZoneName(z.getZoneName()).ifPresent(x -> {
            throw new BadRequestException("zone name must be unique");
        });

        z.setActive(true);
        z.setCreatedAt(Instant.now());
        z.setUpdatedAt(Instant.now());
        return repo.save(z);
    }

    @Override
    public Zone updateZone(Long id, Zone z) {
        Zone existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        existing.setZoneName(z.getZoneName());
        existing.setPriorityLevel(z.getPriorityLevel());
        existing.setPopulation(z.getPopulation());
        existing.setActive(z.getActive());
        existing.setUpdatedAt(Instant.now());

        return repo.save(existing);
    }

    @Override
    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone z = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        z.setActive(false);
        repo.save(z);
    }
}
