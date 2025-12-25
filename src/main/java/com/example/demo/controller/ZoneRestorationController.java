@RestController
@RequestMapping("/api/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService service;

    public ZoneRestorationController(ZoneRestorationService service) {
        this.service = service;
    }

    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }
}
