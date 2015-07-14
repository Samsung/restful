package com.samsung.ax.restful.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samsung.ax.restful.common.Constants;
import com.samsung.ax.restful.persistence.BBSPersistence;
import com.samsung.ax.restful.pojo.UserPrincipal;
import com.samsung.ax.restful.service.BBSService;
import com.samsung.ax.restful.utils.FileHelper;

/**
 * 
 * @author heesik.jeon
 *
 */

public class BBSServiceImpl implements BBSService {

    private static final Logger logger = Logger.getLogger(BBSServiceImpl.class);
    
    @Autowired
    private BBSPersistence persistence;

    /* 
     * @see com.samsung.ax.restful.service.BBSService#uploadUser(com.samsung.ax.restful.pojo.UserPrincipal, java.io.InputStream, java.lang.String)
     */
    @Override
    public Object upload(UserPrincipal user, InputStream is, String filename) throws IOException {
        
        logger.debug("..");
        
        String hash = FileHelper.hashdir(Constants.FILE_BASE_PATH, user.getName(), Constants.FILE_BASE_DEPTH);
        
        File filepath = new File(hash);
        
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        
        String file = new StringBuffer(hash).append(filename).toString();
        logger.debug(file);
        
        OutputStream os = new FileOutputStream(new File(file));
        
        int read = 0;
        byte[] bytes = new byte[4096];
        
        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        
        os.flush();
        os.close();
        
        return filename;
        
    }

}
