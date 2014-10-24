package com.sec.ax.restful.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

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
	
	public void testPropertiesFromClasspath() throws IOException {
		
		Reader reader = null;
		
		try {

			reader = StreamHelper.getReader("/com/sec/ax/restful/utils/file.properties");
			
			Properties prop = new Properties();
			prop.load(reader);
			
			logger.debug(prop.get("project"));
			logger.debug(prop.get("description"));

		} finally {
			reader.close();
		} 
		
	}

}
