package ua.com.foxminded.java8api.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.java8api.collector.DataCollector;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TableCreatorTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @InjectMocks
    TableCreator tableCreator;

    @Mock
    DataCollector mockedDataCollector;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTable_ShouldReturnRacersStandings_WhenInvoked() {
        List<String> racersInfo = Arrays.asList("Sebastian Vettel_FERRARI_PT1M4.415S", "Daniel Ricciardo_RED BULL RACING TAG HEUER_PT1M12.013S",
                "Valtteri Bottas_MERCEDES_PT1M12.434S", "Lewis Hamilton_MERCEDES_PT1M12.46S", "Stoffel Vandoorne_MCLAREN RENAULT_PT1M12.463S",
                "Kimi Raikkonen_FERRARI_PT1M12.639S", "Fernando Alonso_MCLAREN RENAULT_PT1M12.657S", "Sergey Sirotkin_WILLIAMS MERCEDES_PT1M12.706S",
                "Charles Leclerc_SAUBER FERRARI_PT1M12.829S", "Sergio Perez_FORCE INDIA MERCEDES_PT1M12.848S", "Romain Grosjean_HAAS FERRARI_PT1M12.93S",
                "Pierre Gasly_SCUDERIA TORO ROSSO HONDA_PT1M12.941S", "Carlos Sainz_RENAULT_PT1M12.95S", "Esteban Ocon_FORCE INDIA MERCEDES_PT1M13.028S",
                "Nico Hulkenberg_RENAULT_PT1M13.065S", "Brendon Hartley_SCUDERIA TORO ROSSO HONDA_PT1M13.179S", "Marcus Ericsson_SAUBER FERRARI_PT1M13.265S",
                "Lance Stroll_WILLIAMS MERCEDES_PT1M13.323S", "Kevin Magnussen_HAAS FERRARI_PT1M13.393S");

        Map<String, String> racersInfoMap = new LinkedHashMap<>();

        for (String s : racersInfo) {
            racersInfoMap.put(s, s);
        }

        when(mockedDataCollector.getRacersInformation()).thenReturn(racersInfoMap);

        assertEquals("1. Sebastian Vettel   | FERRARI                   | 1:04.415" + LINE_SEPARATOR +
                "2. Daniel Ricciardo   | RED BULL RACING TAG HEUER | 1:12.013" + LINE_SEPARATOR +
                "3. Valtteri Bottas    | MERCEDES                  | 1:12.434" + LINE_SEPARATOR +
                "4. Lewis Hamilton     | MERCEDES                  | 1:12.460" + LINE_SEPARATOR +
                "5. Stoffel Vandoorne  | MCLAREN RENAULT           | 1:12.463" + LINE_SEPARATOR +
                "6. Kimi Raikkonen     | FERRARI                   | 1:12.639" + LINE_SEPARATOR +
                "7. Fernando Alonso    | MCLAREN RENAULT           | 1:12.657" + LINE_SEPARATOR +
                "8. Sergey Sirotkin    | WILLIAMS MERCEDES         | 1:12.706" + LINE_SEPARATOR +
                "9. Charles Leclerc    | SAUBER FERRARI            | 1:12.829" + LINE_SEPARATOR +
                "10. Sergio Perez      | FORCE INDIA MERCEDES      | 1:12.848" + LINE_SEPARATOR +
                "11. Romain Grosjean   | HAAS FERRARI              | 1:12.930" + LINE_SEPARATOR +
                "12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 1:12.941" + LINE_SEPARATOR +
                "13. Carlos Sainz      | RENAULT                   | 1:12.950" + LINE_SEPARATOR +
                "14. Esteban Ocon      | FORCE INDIA MERCEDES      | 1:13.028" + LINE_SEPARATOR +
                "15. Nico Hulkenberg   | RENAULT                   | 1:13.065" + LINE_SEPARATOR +
                "------------------------------------------------------------" + LINE_SEPARATOR +
                "16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 1:13.179" + LINE_SEPARATOR +
                "17. Marcus Ericsson   | SAUBER FERRARI            | 1:13.265" + LINE_SEPARATOR +
                "18. Lance Stroll      | WILLIAMS MERCEDES         | 1:13.323" + LINE_SEPARATOR +
                "19. Kevin Magnussen   | HAAS FERRARI              | 1:13.393" + LINE_SEPARATOR, tableCreator.createTable());
    }
}
