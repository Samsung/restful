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
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object getNotes(Query query, Object object) throws Exception;

	/**
	 * @param idx
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object getNote(int idx, Object object) throws Exception;

	/**
	 * @param note
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object createNote(Note note, Object object) throws Exception;

	/**
	 * @param note
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object updateNote(Note note, Object object) throws Exception;

	/**
	 * @param note
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object deleteNote(Note note, Object object) throws Exception;
	
}
