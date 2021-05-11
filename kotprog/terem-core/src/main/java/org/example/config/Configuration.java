package org.example.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private static Properties props = new Properties();

    static {
        try {
            props.load(Configuration.class.getResourceAsStream("/application.properties"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties getProps(){
        return props;
    }
    public static String getValue(String key){
        return props.getProperty(key);
    }
}
