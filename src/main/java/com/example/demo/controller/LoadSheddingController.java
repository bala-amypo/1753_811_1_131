@RestController
@RequestMapping("/load-shedding")
public class LoadSheddingController {

    private final LoadSheddingService service;

    public LoadSheddingController(LoadSheddingService service) {
        this.service = service;
    }

    @PostMapping("/trigger")
    public LoadSheddingEvent trigger() {
        return service.triggerLoadShedding();
    }
}
