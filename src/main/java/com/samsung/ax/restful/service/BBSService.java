package com.samsung.ax.restful.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.dao.DataAccessException;

import com.samsung.ax.restful.pojo.UserPrincipal;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface BBSService {

    /**
     * @param user
     * @param is
     * @param filename
     * @return
     * @throws DataAccessException
     */
    public Object upload(UserPrincipal user, InputStream is, String filename) throws IOException;
    
}
