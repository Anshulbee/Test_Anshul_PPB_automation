package org.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream("config/api.properties")) {
            System.out.println("Loading properties from config/api.properties");
            System.out.println("InputStream: " + is);
            if (is != null) {
                properties.load(is);
            } else {
                throw new RuntimeException("config.properties file not found in classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String setProperty(String key, String value) {
        return (String) properties.setProperty(key, value);
    }
}
