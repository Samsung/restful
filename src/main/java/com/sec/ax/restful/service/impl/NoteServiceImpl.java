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
	 * @see com.sec.ax.restful.service.NoteService#getNotes(com.sec.ax.restful.pojo.Query, java.lang.Object)
	 */
	@Override
	public Object getNotes(Query query, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.getNotes(query);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#cntNote(java.lang.Object)
	 */
	@Override
	public int cntNote() throws DataAccessException {

        logger.debug("..");
        
        int cnt = persistence.cntNote();
        
		return cnt;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#getNote(int, java.lang.Object)
	 */
	@Override
	public Object getNote(int idx, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.getNote(idx);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#createNote(com.sec.ax.restful.pojo.Note, java.lang.String, java.lang.Object)
	 */
	@Override
	public Object createNote(Note note, String sid, Object object) throws DataAccessException {

        logger.debug("..");
        
        note.setSid(sid);
        
        object = persistence.createNote(note);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#getNote(int, java.lang.Object)
	 */
	@Override
	public String getSid(int idx, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.getSid(idx);
        
		return (String) object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#updateNote(com.sec.ax.restful.pojo.Note, java.lang.Object)
	 */
	@Override
	public Object updateNote(Note note, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.updateNote(note);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#deleteNote(com.sec.ax.restful.pojo.Note, java.lang.Object)
	 */
	@Override
	public Object deleteNote(Note note, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.deleteNote(note);

		return object;
		
	}

}
