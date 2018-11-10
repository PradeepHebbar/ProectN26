package com.n26.main.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.n26.main.exceptions.WebServicesException;

public class BaseAgent {
    
    private InputStream input = null;
    private Properties prop = new Properties();
    
    
    /**
     * Description : Method to initialize config.properties and GlobalData this
     * will be available throughout the execution
     * 
     * @param globalData
     * @throws IOException
     * @throws WebServicesException
     */
    protected void setupProps() throws IOException, WebServicesException {
        try {
            input = new FileInputStream("resources\\config.properties");
            prop.load(input);
        } catch (Exception e) {
            throw new WebServicesException("Failed to read Property file");
        }
        setGlobalData(prop);
    }
    
    public Properties getGlobalData() {
        return prop;
    }

    public void setGlobalData(Properties properties) {
        this.prop = properties;
    }

}
