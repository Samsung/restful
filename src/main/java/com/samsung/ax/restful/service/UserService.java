package com.samsung.ax.restful.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.samsung.ax.restful.crypt.AxCryptException;
import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.pojo.User;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface UserService {

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
     * @param request
     * @param response
     * @param user
     * @return
     * @throws DataAccessException
     * @throws AxCryptException
     */
    public User signin(HttpServletRequest request, HttpServletResponse response, User user) throws DataAccessException, AxCryptException;

    /**
     * @param request
     * @param response
     * @param user
     * @return
     * @throws AxCryptException
     */
    public User cookie(HttpServletRequest request, HttpServletResponse response, User user) throws AxCryptException;
    
    /**
     * @param request
     * @param response
     */
    public void signout(HttpServletRequest request, HttpServletResponse response);

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
