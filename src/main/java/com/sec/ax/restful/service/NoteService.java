package com.sec.ax.restful.service;

import org.springframework.dao.DataAccessException;

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
	 * @throws DataAccessException
	 */
	public Object getNotes(Query query, Object object) throws DataAccessException;

	/**
	 * @return
	 * @throws DataAccessException
	 */
	public int cntNote() throws DataAccessException;
	
	/**
	 * @param idx
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object getNote(int idx, Object object) throws DataAccessException;

	/**
	 * @param note
	 * @param sid
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object createNote(Note note, String sid, Object object) throws DataAccessException;

	/**
	 * @param idx
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public String getSid(int idx, Object object) throws DataAccessException;

	/**
	 * @param note
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object updateNote(Note note, Object object) throws DataAccessException;

	/**
	 * @param note
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public Object deleteNote(Note note, Object object) throws DataAccessException;

}
