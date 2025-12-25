@RestController
@RequestMapping("/demand-readings")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    @GetMapping("/zone/{zoneId}")
    public List<DemandReading> getByZone(@PathVariable Long zoneId) {
        return service.getReadingsForZone(zoneId);
    }
}
