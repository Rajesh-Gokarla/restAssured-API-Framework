package config;

import java.io.InputStream;
import java.util.Properties;

import endPoints.Operation;

public class config {

    private static final Properties ENV = new Properties();
    private static final Properties URLS = new Properties();
    private static final Properties AREA = new Properties();

    static {
        try {
            // Load env + area selector
            load("config/env.properties", ENV);

            String env = System.getProperty("env", ENV.getProperty("env"));
            String area = System.getProperty("area", ENV.getProperty("area"));

            if (env == null || area == null) {
                throw new RuntimeException("env / area not specified");
            }

            // Load env-level config (base URLs, auth, etc.)
            load("config/" + env + ".properties", URLS);

            // 🔥 Load SINGLE area file (dkp1120.properties)
            load("areas/" + area + ".properties", AREA);

        } catch (Exception e) {
            throw new RuntimeException("Config load failed", e);
        }
    }

    private static void load(String path, Properties p) throws Exception {
        try (InputStream is =
                     config.class.getClassLoader()
                             .getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Missing config file: " + path);
            }
            p.load(is);
        }
    }

    // ===== ACCESS METHODS =====

    public static String baseUrl(String service) {
        return URLS.getProperty(service + ".base.url");
    }

    public static String endpoint(Operation operation) {
        return AREA.getProperty(operation.name());
    }

    public static String value(String key) {
        return URLS.getProperty(key);
    }
}