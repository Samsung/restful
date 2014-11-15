package com.sec.ax.restful.persistence;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Query;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface NotePersistence {
	
	/**
	 * @param query
	 * @return
	 * @throws DataAccessException
	 */
	public List<Note> getNotes(Query query) throws DataAccessException;

	/**
	 * @return
	 * @throws DataAccessException
	 */
	public int cntNote() throws DataAccessException;

	/**
	 * @param idx
	 * @return
	 * @throws DataAccessException
	 */
	public Note getNote(int idx) throws DataAccessException;

	/**
	 * @param note
	 * @return
	 * @throws DataAccessException
	 */
	public int createNote(Note note) throws DataAccessException;

	/**
	 * @param idx
	 * @return
	 * @throws DataAccessException
	 */
	public String getSid(int idx) throws DataAccessException;

	/**
	 * @param note
	 * @return
	 * @throws DataAccessException
	 */
	public int updateNote(Note note) throws DataAccessException;
	
	/**
	 * @param note
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteNote(Note note) throws DataAccessException;

}
