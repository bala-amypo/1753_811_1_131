@RestController
@RequestMapping("/api/forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    // already exists
    @GetMapping("/latest")
    public SupplyForecast getLatest() {
        return service.getLatestForecast();
    }

    // ➕ ADD
    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast forecast) {
        return service.createForecast(forecast);
    }

    // ➕ ADD
    @GetMapping
    public List<SupplyForecast> getAll() {
        return service.getAllForecasts();
    }
}
