package com.fotoware.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    public static String readFromProperties(String variableName) {
        String result = "";
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("../testing.properties");
            properties.load(input);
            result = properties.getProperty(variableName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
