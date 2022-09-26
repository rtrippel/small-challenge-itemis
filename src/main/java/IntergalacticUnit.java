import java.util.HashMap;
import java.util.Map;

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

    public void addConformity(String key, String value) {
        conformityMap.put(key, value);
    }

    public String getValue(String key) {
        return conformityMap.get(key);
    }
}
