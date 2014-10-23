package com.sec.ax.restful.service;

import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Query;


/**
 * 
 * @author heesik.jeon
 *
 */

public interface NoteService {

	/**
	 * @param query
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getNotes(Query query, Object response) throws Exception;

	/**
	 * @param idx
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getNote(int idx, Object response) throws Exception;

	/**
	 * @param note
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object createNote(Note note, Object response) throws Exception;

	/**
	 * @param note
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object updateNote(Note note, Object response) throws Exception;

	/**
	 * @param note
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object deleteNote(Note note, Object response) throws Exception;
	
}
