package com.samsung.ax.restful.crypt.aes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.samsung.ax.restful.crypt.AxCryptException;
import com.samsung.ax.restful.crypt.aes.AxCrypt;

import junit.framework.TestCase;

/**
 *
 * @author heesik.jeon
 *
 */

public class AxCryptTest extends TestCase {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    public AxCryptTest() {
        logger.setLevel(Level.DEBUG);
    }
    
    public void testEncrypt() {
        
        try {
            
            String plain = "대한민국 Korea 韓國  ~!@#$%^&*()_+|";
            String encrypted = AxCrypt.encrypt("Vvb7oruYPkOHrEwoTVnXtw==", plain);
            
            logger.debug(plain);
            logger.debug(encrypted);
            
        } catch (AxCryptException e) {
            fail(e.toString());
        }
        
    }

    public void testDecrypt() {
        
        try {
            
            String encrypted = "yhAGrcZInqeKNvMiLiBugeIT/Sb/4ldr8YMLaL0OrRmvLc/pHJ8YZTda0R63HRyC";
            String decrypted = AxCrypt.decrypt("Vvb7oruYPkOHrEwoTVnXtw==", encrypted);
            
            logger.debug(encrypted);
            logger.debug(decrypted);
            
        } catch (AxCryptException e) {
            fail(e.toString());
        }
        
    }

}
