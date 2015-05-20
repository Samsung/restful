package com.sec.ax.restful.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.persistence.NotePersistence;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.service.NoteService;

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
     * @see com.sec.ax.restful.service.NoteService#create(com.sec.ax.restful.pojo.Note)
     */
    @Override
    public int create(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.create(note);
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#sid(int)
     */
    @Override
    public String sid(int idx) throws DataAccessException {

        logger.debug("..");
        
        return persistence.sid(idx);
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#update(com.sec.ax.restful.pojo.Note)
     */
    @Override
    public int update(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.update(note);
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#delete(com.sec.ax.restful.pojo.Note)
     */
    @Override
    public int delete(Note note) throws DataAccessException {

        logger.debug("..");
        
        return persistence.delete(note);
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#idx(int)
     */
    @Override
    public Note idx(int idx) throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.idx(idx);
        
    }
    
    /* 
     * @see com.sec.ax.restful.service.NoteService#access(int)
     */
    public int access(int idx) throws DataAccessException {

        logger.debug("..");

    	return persistence.access(idx);
    	
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#count()
     */
    @Override
    public int count() throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.count();
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#list(com.sec.ax.restful.pojo.Query)
     */
    @Override
    public List<Note> list(Query query) throws DataAccessException {
    
        logger.debug("..");
        
        return persistence.list(query);
        
    }

}
