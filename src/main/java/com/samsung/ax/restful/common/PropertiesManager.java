package com.samsung.ax.restful.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author heesik.jeon
 *
 */

public class PropertiesManager {
    
    private static final Logger logger = Logger.getLogger(PropertiesManager.class);
    
    private Properties properties;

    public PropertiesManager() {
        
        logger.debug("..");
        
        InputStream is = null;
        
        try {
            is = this.getClass().getResourceAsStream(Constants.PROPERTIES_CLASSPATH);
            
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
                
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
