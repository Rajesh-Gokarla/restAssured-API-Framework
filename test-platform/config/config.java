package config;

import endPoints.Operation;
import endPoints.Service;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties ENV = new Properties();
    private static final Properties URLS = new Properties();
    private static final Properties AREA = new Properties();

    static {
        try {
            load("config/env.properties", ENV);

            String env = System.getProperty("env", ENV.getProperty("env"));
            String area = System.getProperty("area", ENV.getProperty("area"));

            if (env == null || area == null) {
                throw new RuntimeException("env/area not specified");
            }

            load("config/" + env + ".properties", URLS);
            load("area/" + area + ".properties", AREA);
        } catch (Exception e) {
            throw new RuntimeException("Config load failed", e);
        }
    }

    private static void load(String path, Properties props) throws Exception {
        try (InputStream is =
                     Config.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Missing file: " + path);
            props.load(is);
        }
    }

    public static String baseUrl(Service service) {
        return URLS.getProperty(service.name() + ".base.url");
    }

    public static String endpoint(Operation op) {
        return AREA.getProperty(op.name());
    }

    public static String value(String key) {
        return URLS.getProperty(key);
    }

    public static String get(String key) {
        String v = AREA.getProperty(key);
        if (v == null) v = URLS.getProperty(key);
        if (v == null) v = ENV.getProperty(key);
        return v;
    }
}