package com.sec.ax.restful.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object getUsers(Query query, Object object) throws Exception;

	/**
	 * @param name
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object getUser(String name, Object object) throws Exception;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object createUser(User user, Object object) throws Exception;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object updateUser(User user, Object object) throws Exception;
	
	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object deleteUser(User user, Object object) throws Exception;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public User loginUser(User user, Object object) throws Exception;

	/**
	 * @param request
	 * @param response
	 */
	public void expiryCookie(HttpServletRequest request, HttpServletResponse response);

}
