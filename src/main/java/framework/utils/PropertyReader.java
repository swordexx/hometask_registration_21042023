package main.java.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    static Properties properties;

    public PropertyReader(){
        loadProperties();
    }

    public void loadProperties() {
        properties = new Properties();
        try {
            String fileName = System.getProperty("user.dir") + "/src/main/resources/test.properties";
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException("No such file.");
        }
    }

    public static String readProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}