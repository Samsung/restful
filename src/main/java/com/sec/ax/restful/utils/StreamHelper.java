package com.sec.ax.restful.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;

/**
 *
 * @author heesik.jeon
 *
 */

public class StreamHelper {
	
	private static final Logger logger = Logger.getLogger(StreamHelper.class);

	/**
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Reader getReader(String path) throws IOException {
		
		logger.debug("..");
		
		InputStream is = StreamHelper.class.getClass().getResourceAsStream(path);
		
		Reader reader = new InputStreamReader(is, "utf-8");
		
		return reader;

	}

}
