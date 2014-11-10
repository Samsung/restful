package com.sec.ax.restful.persistence;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.pojo.Query;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface UserPersistence {
	
	/**
	 * @param query
	 * @return
	 * @throws DataAccessException
	 */
	public List<User> getUsers(Query query) throws DataAccessException;

	/**
	 * @return
	 * @throws DataAccessException
	 */
	public int cntUser() throws DataAccessException;

	/**
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public User getUser(String name) throws DataAccessException;

	/**
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int createUser(User user) throws DataAccessException;

	/**
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int updateSid(User user) throws DataAccessException;

	/**
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int updateUser(User user) throws DataAccessException;
	
	/**
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteUser(User user) throws DataAccessException;

	/**
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public User loginUser(User user) throws DataAccessException;

}
