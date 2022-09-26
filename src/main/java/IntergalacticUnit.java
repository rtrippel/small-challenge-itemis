import java.util.HashMap;
import java.util.Map;

/**
 * The class is designed to store and process the ratio of Intergalactic units - Roman numerals
 */
public class IntergalacticUnit {

    private static IntergalacticUnit instance;

    private static final Map<String, String> conformityMap = new HashMap<>();

    private IntergalacticUnit() {}

    public static IntergalacticUnit getInstance() {
        if (instance == null) {
            instance = new IntergalacticUnit();
        }
        return instance;
    }

    public void addConformity(String iUnit, String romanNumeral) {
        conformityMap.put(iUnit, romanNumeral);
    }

    public String getValue(String key) {
        return conformityMap.get(key);
    }
}
