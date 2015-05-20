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
     * @param note
     * @return
     * @throws DataAccessException
     */
    public int create(Note note) throws DataAccessException;

    /**
     * @param idx
     * @return
     * @throws DataAccessException
     */
    public String sid(int idx) throws DataAccessException;

    /**
     * @param note
     * @return
     * @throws DataAccessException
     */
    public int update(Note note) throws DataAccessException;
    
    /**
     * @param note
     * @return
     * @throws DataAccessException
     */
    public int delete(Note note) throws DataAccessException;

    /**
     * @param idx
     * @return
     * @throws DataAccessException
     */
    public Note idx(int idx) throws DataAccessException;

    /**
     * @param idx
     * @return
     * @throws DataAccessException
     */
    public int access(int idx) throws DataAccessException;

    /**
     * @return
     * @throws DataAccessException
     */
    public int count() throws DataAccessException;

    /**
     * @param query
     * @return
     * @throws DataAccessException
     */
    public List<Note> list(Query query) throws DataAccessException;

}
