package com.sec.ax.restful.utils;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author heesik.jeon
 *
 */

public class FormatHelper {
	
	private static final Logger logger = Logger.getLogger(FormatHelper.class);

    /**
     * @param object
     * @return
     */
    public static String printPretty(Object object) {
    	
		logger.debug("..");

    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	String json = gson.toJson(object);
    	
    	return json;
    	
    }

}
