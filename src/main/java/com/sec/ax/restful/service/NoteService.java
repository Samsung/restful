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
     * @param note
     * @param sid
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object create(Note note, String sid, Object object) throws DataAccessException;

    /**
     * @param idx
     * @param object
     * @return
     * @throws DataAccessException
     */
    public String sid(int idx, Object object) throws DataAccessException;

    /**
     * @param note
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object update(Note note, Object object) throws DataAccessException;

    /**
     * @param note
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object delete(Note note, Object object) throws DataAccessException;

    /**
     * @param idx
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object idx(int idx, Object object) throws DataAccessException;

    /**
     * @return
     * @throws DataAccessException
     */
    public int count() throws DataAccessException;
    
    /**
     * @param query
     * @param object
     * @return
     * @throws DataAccessException
     */
    public Object list(Query query, Object object) throws DataAccessException;

}
