package com.samsung.ax.restful.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.samsung.ax.restful.common.Constants;
import com.samsung.ax.restful.utils.FileHelper;

/**
 *
 * @author heesik.jeon
 *
 */

public class FileHelperTest extends TestCase {

    private Logger logger = Logger.getLogger(this.getClass());
    
    public FileHelperTest() {
        logger.setLevel(Level.DEBUG);
    }
    
    public void testReadProperties() throws IOException {
        
        Reader reader = null;
        
        try {

            reader = FileHelper.getStream("/com/samsung/ax/restful/utils/project.properties");
            
            Properties prop = new Properties();
            prop.load(reader);
            
            logger.debug(prop.get("project"));
            logger.debug(prop.getProperty("description"));

        } catch (Exception e) {
            fail(e.toString());
        } finally {
            reader.close();
        }
        
    }

    public void testReadLine() throws IOException {
        
        Reader reader = null;
        
        try {

            reader = FileHelper.getStream("/com/samsung/ax/restful/utils/project.properties");
            
            BufferedReader br = new BufferedReader(reader);
            
            String line;
            
            while ((line = br.readLine()) != null) {
                logger.debug(line);
            }

        } catch (Exception e) {
            fail(e.toString());
        } finally {
            reader.close();
        }
        
    }

    public void testDir() {
        
        try {
            
            String input = "ax";
            int depth = 2;
            
            logger.debug(FileHelper.hashdir(Constants.FILE_BASE_PATH, input, depth));
            
        } catch (Exception e) {
            fail(e.toString());
        }

    }

}
