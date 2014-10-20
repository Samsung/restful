package com.sec.ax.restful.persistence;

import java.util.List;

import com.sec.ax.restful.pojo.Note;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface NotePersistence {
	
	/**
	 * @param pn
	 * @return
	 * @throws Exception
	 */
	public List<Note> getNotes(int pn) throws Exception;

	/**
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public List<Note> getNote(int idx) throws Exception;

	/**
	 * @param note
	 * @return
	 * @throws Exception
	 */
	public Integer createNote(Note note) throws Exception;

	/**
	 * @param note
	 * @return
	 * @throws Exception
	 */
	public int updateNote(Note note) throws Exception;
	
	/**
	 * @param note
	 * @return
	 * @throws Exception
	 */
	public int deleteNote(Note note) throws Exception;

}
