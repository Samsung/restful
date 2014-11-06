package com.sec.ax.restful.persistence;

import java.util.List;

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
	 * @throws Exception
	 */
	public List<User> getUsers(Query query) throws Exception;

	/**
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<User> getUser(String name) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int createUser(User user) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateSid(User user) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception;
	
	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(User user) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User loginUser(User user) throws Exception;

}
