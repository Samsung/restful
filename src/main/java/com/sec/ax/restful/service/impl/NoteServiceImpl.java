package com.sec.ax.restful.service.impl;

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
     * @see com.sec.ax.restful.service.NoteService#create(com.sec.ax.restful.pojo.Note, java.lang.String, java.lang.Object)
     */
    @Override
    public Object create(Note note, String sid, Object object) throws DataAccessException {

        logger.debug("..");
        
        note.setSid(sid);
        
        object = persistence.create(note);

        return object;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#sid(int, java.lang.Object)
     */
    @Override
    public String sid(int idx, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.sid(idx);
        
        return (String) object;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#update(com.sec.ax.restful.pojo.Note, java.lang.Object)
     */
    @Override
    public Object update(Note note, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.update(note);

        return object;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#delete(com.sec.ax.restful.pojo.Note, java.lang.Object)
     */
    @Override
    public Object delete(Note note, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.delete(note);

        return object;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#idx(int, java.lang.Object)
     */
    @Override
    public Object idx(int idx, Object object) throws DataAccessException {
    
        logger.debug("..");
        
        object = persistence.idx(idx);
        
        return object;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#count()
     */
    @Override
    public int count() throws DataAccessException {
    
        logger.debug("..");
        
        int cnt = persistence.count();
        
        return cnt;
        
    }

    /* 
     * @see com.sec.ax.restful.service.NoteService#list(com.sec.ax.restful.pojo.Query, java.lang.Object)
     */
    @Override
    public Object list(Query query, Object object) throws DataAccessException {
    
        logger.debug("..");
        
        object = persistence.list(query);
        
        return object;
        
    }

}
