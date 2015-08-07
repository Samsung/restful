package com.samsung.ax.restful.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.samsung.ax.restful.persistence.NotePersistence;
import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.pojo.Paging;
import com.samsung.ax.restful.pojo.Query;

/**
 * 
 * @author heesik.jeon
 *
 */

public class NotePersistenceImpl extends SqlMapClientDaoSupport implements NotePersistence {

    private static final Logger logger = Logger.getLogger(NotePersistenceImpl.class);

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#create(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int create(Note note) throws DataAccessException {

        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().insert("note.create", note);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#sid(int)
     */
    @Override
    public String sid(int idx) throws DataAccessException {

        logger.debug("..");
        
        return (String) getSqlMapClientTemplate().queryForObject("note.sid", idx);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#update(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int update(Note note) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().update("note.update", note);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#delete(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int delete(Note note) throws DataAccessException {
        
        logger.debug("..");
        
        return getSqlMapClientTemplate().delete("note.delete", note);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#idx(int)
     */
    @Override
    public Note idx(int idx) throws DataAccessException {
    
        logger.debug("..");
        
        return (Note) getSqlMapClientTemplate().queryForObject("note.idx", idx);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#access(int)
     */
    @Override
    public int access(int idx) throws DataAccessException {
    
        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().update("note.access", idx);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#count(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public int count(Query query) throws DataAccessException {
        
        logger.debug("..");
        
        return (int) getSqlMapClientTemplate().queryForObject("note.count", query);
        
    }

    /* 
     * @see com.samsung.ax.restful.persistence.NotePersistence#list(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public List<Note> list(Query query) throws DataAccessException {
    
        logger.debug("..");
    
        Paging paging = query.getPaging();
        
        @SuppressWarnings("unchecked")
        List<Note> list = getSqlMapClientTemplate().queryForList("note.list", query, paging.getSkipResults(), paging.getMaxResults());
        
        return list;
        
    }
    
}