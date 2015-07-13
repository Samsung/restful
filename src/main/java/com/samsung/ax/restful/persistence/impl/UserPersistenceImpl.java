package com.samsung.ax.restful.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.samsung.ax.restful.persistence.UserPersistence;
import com.samsung.ax.restful.pojo.Paging;
import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.pojo.User;

/**
 * 
 * @author heesik.jeon
 *
 */

public class UserPersistenceImpl extends SqlMapClientDaoSupport implements UserPersistence {

    private static final Logger logger = Logger.getLogger(UserPersistenceImpl.class);
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#signup(com.samsung.ax.restful.pojo.User)
     */
    @Override
    public int signup(User user) throws DataAccessException {

        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().insert("user.signup", user);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#sid(com.samsung.ax.restful.pojo.User)
     */
    @Override
    public int sid(User user) throws DataAccessException {

        logger.debug("..");
        
        return getSqlMapClientTemplate().update("user.sid", user);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#signin(com.samsung.ax.restful.pojo.User)
     */
    @Override
    public User signin(User user) throws DataAccessException {

        logger.debug("..");
        
        return (User) getSqlMapClientTemplate().queryForObject("user.signin", user);

    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#update(com.samsung.ax.restful.pojo.User)
     */
    @Override
    public int update(User user) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().update("user.update", user);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#delete(com.samsung.ax.restful.pojo.User)
     */
    @Override
    public int delete(User user) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().delete("user.delete", user);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#name(java.lang.String)
     */
    @Override
    public User name(String name) throws DataAccessException {

        logger.debug("..");
        
        return (User) getSqlMapClientTemplate().queryForObject("user.name", name);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#count(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public int count(Query query) throws DataAccessException {
        
        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().queryForObject("user.count", query);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.UserPersistence#list(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public List<User> list(Query query) throws DataAccessException {

        logger.debug("..");

        Paging paging = query.getPaging();
        
        @SuppressWarnings("unchecked")
        List<User> list = getSqlMapClientTemplate().queryForList("user.list", query, paging.getSkipResults(), paging.getMaxResults());
        
        return list;
        
    }

}