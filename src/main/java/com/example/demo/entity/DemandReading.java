@Entity
@Getter @Setter
public class DemandReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long zoneId;

    private Double demandMW;

    private Instant recordedAt;   // 🔥 THIS IS THE KEY FIELD
}
