package com.samsung.ax.restful.utils;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author heesik.jeon
 *
 */

public class StreamHelperTest extends TestCase {

    private Logger logger = Logger.getLogger(this.getClass());
    
    public StreamHelperTest() {
        logger.setLevel(Level.DEBUG);
    }
    
}
