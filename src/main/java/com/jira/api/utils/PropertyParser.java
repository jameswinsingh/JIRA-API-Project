package com.jira.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is to read the property from properties file.
 */
public class PropertyParser {
    private final Properties properties;

    public PropertyParser(String propertyFilePath) {
        properties = new Properties();
        loadProperties(propertyFilePath);
    }

    private void loadProperties(String propertyFilePath) {
        File propertyFile = new File(propertyFilePath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(propertyFile);
            properties.load(fileInputStream);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
