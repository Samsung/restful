package com.sec.ax.restful.utils;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
	
	public void testDir() {
		
		try {
			
	        String base = "user";
			String input = "ax";
			int depth = 2;
			
			logger.debug(FileHelper.hashdir(base, input, depth));
	        
		} catch (Exception e) {
			fail(e.toString());
		}

	}

}
