package com.sec.ax.restful.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sec.ax.restful.persistence.UserPersistence;
import com.sec.ax.restful.pojo.Paging;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.User;

/**
 * 
 * @author heesik.jeon
 *
 */

public class UserPersistenceImpl extends SqlMapClientDaoSupport implements UserPersistence {

    private static final Logger logger = Logger.getLogger(UserPersistenceImpl.class);
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#signup(com.sec.ax.restful.pojo.User)
     */
    @Override
    public int signup(User user) throws DataAccessException {

        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().insert("user.createUser", user);
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#sid(com.sec.ax.restful.pojo.User)
     */
    @Override
    public int sid(User user) throws DataAccessException {

        logger.debug("..");
        
        return getSqlMapClientTemplate().update("user.updateSid", user);
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#signin(com.sec.ax.restful.pojo.User)
     */
    @Override
    public User signin(User user) throws DataAccessException {

        logger.debug("..");
        
        user = (User) getSqlMapClientTemplate().queryForObject("user.loginUser", user);
        
        return user;

    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#update(com.sec.ax.restful.pojo.User)
     */
    @Override
    public int update(User user) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().update("user.updateUser", user);
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#delete(com.sec.ax.restful.pojo.User)
     */
    @Override
    public int delete(User user) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().delete("user.deleteUser", user);
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#name(java.lang.String)
     */
    @Override
    public User name(String name) throws DataAccessException {

        logger.debug("..");
        
        User user = (User) getSqlMapClientTemplate().queryForObject("user.getUser", name);
        
        return user;
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#count()
     */
    @Override
    public int count() throws DataAccessException {
        
        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().queryForObject("user.cntUser");
        
    }
    
    /* 
     * @see com.sec.ax.restful.persistence.UserPersistence#list(com.sec.ax.restful.pojo.Query)
     */
    @Override
    public List<User> list(Query query) throws DataAccessException {

        logger.debug("..");

        Paging paging = query.getPaging();
        
        @SuppressWarnings("unchecked")
        List<User> list = getSqlMapClientTemplate().queryForList("user.getUsers", query, paging.getSkipResults(), paging.getMaxResults());
        
        return list;
        
    }

}