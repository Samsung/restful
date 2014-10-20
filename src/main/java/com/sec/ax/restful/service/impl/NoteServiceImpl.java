package com.sec.ax.restful.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.sec.ax.restful.persistence.NotePersistence;
import com.sec.ax.restful.pojo.Note;
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
	 * @see com.sec.ax.restful.service.NoteService#getNotes(int, java.lang.Object)
	 */
	@Override
	public Object getNotes(int pn, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.getNotes(pn);
        
		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#getNote(int, java.lang.Object)
	 */
	@Override
	public Object getNote(int idx, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.getNote(idx);
        
		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#createNote(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object createNote(Object request, Object response) throws Exception {

        logger.debug("..");
        
        Gson gson = new Gson();

        Note note = gson.fromJson(gson.toJson(request), Note.class);

        response = persistence.createNote(note);

		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#updateNote(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object updateNote(Object request, Object response) throws Exception {

        logger.debug("..");
        
        Gson gson = new Gson();
        
        Note note = gson.fromJson(gson.toJson(request), Note.class);

        response = persistence.updateNote(note);

		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.NoteService#deleteNote(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object deleteNote(Object request, Object response) throws Exception {

        logger.debug("..");
        
        Gson gson = new Gson();
        
        Note note = gson.fromJson(gson.toJson(request), Note.class);

        response = persistence.deleteNote(note);

		return response;
		
	}

}
