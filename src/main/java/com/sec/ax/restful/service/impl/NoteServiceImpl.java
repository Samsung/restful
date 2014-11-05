package com.sec.ax.restful.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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
	public Object getNotes(Query query, Object object) throws Exception {

        logger.debug("..");
        
        object = persistence.getNotes(query);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#getNote(int, java.lang.Object)
	 */
	@Override
	public Object getNote(int idx, Object object) throws Exception {

        logger.debug("..");
        
        object = persistence.getNote(idx);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#createNote(com.sec.ax.restful.pojo.Note, java.lang.Object)
	 */
	@Override
	public Object createNote(Note note, Object object) throws Exception {

        logger.debug("..");
        
        object = persistence.createNote(note);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#updateNote(com.sec.ax.restful.pojo.Note, java.lang.Object)
	 */
	@Override
	public Object updateNote(Note note, Object object) throws Exception {

        logger.debug("..");
        
        object = persistence.updateNote(note);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#deleteNote(com.sec.ax.restful.pojo.Note, java.lang.Object)
	 */
	@Override
	public Object deleteNote(Note note, Object object) throws Exception {

        logger.debug("..");
        
        object = persistence.deleteNote(note);

		return object;
		
	}

}
