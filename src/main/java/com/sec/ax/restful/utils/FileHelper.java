package com.sec.ax.restful.utils;

import java.io.File;

import org.apache.log4j.Logger;

/**
 *
 * @author heesik.jeon
 *
 */

public class FileHelper {
	
	private static final Logger logger = Logger.getLogger(FileHelper.class);

	private static final int RANDOM_SEED = 1132164512;
	private static final int RANDOM_MAX = 0x7fff;

	private static final int ALPHA_MAX = 26;
	private static final int ALPHA_START = 97;

    public static String dir(String base, String input, int depth) {
    	
		logger.debug("..");

        StringBuffer folder;

        if (base.endsWith("/")) {
        	folder = new StringBuffer(base);
        } else {
        	folder = new StringBuffer(base + "/");
        }

		long hash = input.hashCode() & 0xffffffffL;
		
        for (int i = 0; i < depth; i++) {

            int mod = (int) (hash % ALPHA_MAX);
            folder.append((char) (ALPHA_START + mod));
            folder.append('/');
            
            hash = (hash * RANDOM_SEED) / (2 * (RANDOM_MAX+1)) % (RANDOM_MAX +1);

        }

    	return folder.toString();
    	
    }
    
    public static void mkdir(String foldername) {
    	
		logger.debug("..");

		File folder = new File(foldername);

    	if (!folder.exists()) {
			folder.mkdirs();
		}

    }

}
