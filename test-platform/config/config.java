package config;

import endPoints.Operation;
import endPoints.Service;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties ENV = new Properties();

    static {
        try {
            Properties selector = new Properties();
            load("config/env.properties", selector);

            String env = System.getProperty("env", selector.getProperty("env"));
            if (env == null) throw new RuntimeException("env not specified");

            load("environments/" + env + ".properties", ENV);

        } catch (Exception e) {
            throw new RuntimeException("Config init failed", e);
        }
    }

    private static void load(String path, Properties p) throws Exception {
        try (InputStream is =
                     Config.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Missing: " + path);
            p.load(is);
        }
    }

    public static String baseUrl(Service service) {
        return ENV.getProperty(service.name());
    }

    public static String baseUrl(String service) {
        return ENV.getProperty(service);
    }

    public static String endpoint(Operation op) {
        return ENV.getProperty(op.name());
    }

    public static String value(String key) {
        return ENV.getProperty(key);
    }
}