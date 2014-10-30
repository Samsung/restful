package com.sec.ax.restful.service;

import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.pojo.Query;


/**
 * 
 * @author heesik.jeon
 *
 */

public interface UserService {

	/**
	 * @param query
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getUsers(Query query, Object response) throws Exception;

	/**
	 * @param name
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getUser(String name, Object response) throws Exception;

	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object createUser(User user, Object response) throws Exception;

	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object updateUser(User user, Object response) throws Exception;

	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object deleteUser(User user, Object response) throws Exception;
	
}
