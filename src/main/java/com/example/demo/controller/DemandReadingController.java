@RestController
@RequestMapping("/api/demand-readings")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    @PostMapping
    public DemandReading create(@RequestBody DemandReading r) {
        return service.create(r);
    }

    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }
}
