package com.samsung.ax.restful.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.samsung.ax.restful.persistence.NotePersistence;
import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.service.NoteService;

/**
 * 
 * @author heesik.jeon
 *
 */

public class NoteServiceImpl implements NoteService {

    private static final Logger logger = Logger.getLogger(NoteServiceImpl.class);
    
    @Autowired
    private NotePersistence persistence;

    /* 
     * @see com.samsung.ax.restful.service.NoteService#create(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int create(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.create(note);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#sid(int)
     */
    @Override
    public String sid(int idx) throws DataAccessException {

        logger.debug("..");
        
        return persistence.sid(idx);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#update(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int update(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.update(note);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#delete(com.samsung.ax.restful.pojo.Note)
     */
    @Override
    public int delete(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.delete(note);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#idx(int)
     */
    @Override
    public Note idx(int idx) throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.idx(idx);
        
    }
    
    /* 
     * @see com.samsung.ax.restful.service.NoteService#access(int)
     */
    public int access(int idx) throws DataAccessException {

        logger.debug("..");

        return persistence.access(idx);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#count(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public int count(Query query) throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.count(query);
        
    }

    /* 
     * @see com.samsung.ax.restful.service.NoteService#list(com.samsung.ax.restful.pojo.Query)
     */
    @Override
    public List<Note> list(Query query) throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.list(query);
        
    }

}
