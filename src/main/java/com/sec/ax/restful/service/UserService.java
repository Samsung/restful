package com.sec.ax.restful.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.User;


/**
 * 
 * @author heesik.jeon
 *
 */

public interface UserService {

    /**
     * @param user
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object signup(User user, Object object) throws DataAccessException;

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
    public User cookie(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) throws AxCryptException;
    
    /**
     * @param request
     * @param response
     */
    public void signout(HttpServletRequest request, HttpServletResponse response);

    /**
     * @param user
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object update(User user, Object object) throws DataAccessException;
    
    /**
     * @param user
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object delete(User user, Object object) throws DataAccessException;
    
    /**
     * @param name
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object name(String name, Object object) throws DataAccessException;
    
    /**
     * @param query
     * @return
     * @throws DataAccessException
     */
    public int count(Query query) throws DataAccessException;
    
    /**
     * @param query
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object list(Query query, Object object) throws DataAccessException;

}
