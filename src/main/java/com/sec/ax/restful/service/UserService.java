package com.sec.ax.restful.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

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
	 * @throws DataAccessException
	 */
	public Object getUsers(Query query, Object object) throws DataAccessException;

	/**
	 * @param name
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object getUser(String name, Object object) throws DataAccessException;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object createUser(User user, Object object) throws DataAccessException;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object updateUser(User user, Object object) throws DataAccessException;
	
	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object deleteUser(User user, Object object) throws DataAccessException;

	/**
	 * @param user
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public User loginUser(User user) throws DataAccessException;

	/**
	 * @param request
	 * @param response
	 */
	public void expiryCookie(HttpServletRequest request, HttpServletResponse response);

}
