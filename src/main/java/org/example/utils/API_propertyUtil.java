package org.example.utils;

import java.io.InputStream;
import java.util.Properties;


public class API_propertyUtil {
    private static Properties properties = new Properties();

    public static String getProperty(String propname,String key) {
        try (InputStream is = API_propertyUtil.class.getClassLoader().getResourceAsStream(propname)) {
            System.out.println("Loading properties from " + propname);
            System.out.println("InputStream: " + is);
            if (is != null) {
                properties.load(is);
            } else {
                throw new RuntimeException(propname + " file not found in classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + propname, e);
        }
        return properties.getProperty(key);
    }
}
