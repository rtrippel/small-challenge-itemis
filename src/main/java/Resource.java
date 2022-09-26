import java.util.HashMap;
import java.util.Map;

public class Resource {

    private static Resource instance;

    private final static Map<String, Double> conformityMap = new HashMap<>();

    private Resource() {}

    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
        }
        return instance;
    }

    public void addResourceNameAndCoast(String resourceName, Double cost) {
        conformityMap.put(resourceName, cost);
    }

    public Double getCost(String resourceName) {
        return conformityMap.get(resourceName);
    }
}
