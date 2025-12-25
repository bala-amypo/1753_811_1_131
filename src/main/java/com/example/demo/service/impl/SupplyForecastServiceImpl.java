@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repo;

    public SupplyForecastServiceImpl(SupplyForecastRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast s) {
        validate(s);
        s.setGeneratedAt(Instant.now());
        return repo.save(s);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast s) {
        SupplyForecast existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        validate(s);
        existing.setAvailableSupplyMW(s.getAvailableSupplyMW());
        existing.setForecastStart(s.getForecastStart());
        existing.setForecastEnd(s.getForecastEnd());

        return repo.save(existing);
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return repo.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No forecasts"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return repo.findAll();
    }

    private void validate(SupplyForecast s) {
        if (s.getAvailableSupplyMW() < 0)
            throw new BadRequestException(">= 0");

        if (!s.getForecastStart().isBefore(s.getForecastEnd()))
            throw new BadRequestException("range");
    }
}
