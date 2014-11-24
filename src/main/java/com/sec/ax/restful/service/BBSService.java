package com.sec.ax.restful.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.pojo.User;


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
	public Object uploadFile(User user, InputStream is, String filename) throws IOException;
	
}
