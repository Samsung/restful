package com.samsung.ax.restful.persistence;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.pojo.User;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface UserPersistence {
    
    /**
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int signup(User user) throws DataAccessException;

    /**
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int sid(User user) throws DataAccessException;
    
    /**
     * @param user
     * @return
     * @throws DataAccessException
     */
    public User signin(User user) throws DataAccessException;

    /**
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int update(User user) throws DataAccessException;
    
    /**
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int delete(User user) throws DataAccessException;
    
    /**
     * @param name
     * @return
     * @throws DataAccessException
     */
    public User name(String name) throws DataAccessException;
    
    /**
     * @param query
     * @return
     * @throws DataAccessException
     */
    public int count(Query query) throws DataAccessException;

    /**
     * @param query
     * @return
     * @throws DataAccessException
     */
    public List<User> list(Query query) throws DataAccessException;

}
