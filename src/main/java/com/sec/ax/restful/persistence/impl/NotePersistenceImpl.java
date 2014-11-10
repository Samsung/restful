package com.sec.ax.restful.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sec.ax.restful.persistence.NotePersistence;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Paging;
import com.sec.ax.restful.pojo.Query;

/**
 * 
 * @author heesik.jeon
 *
 */

public class NotePersistenceImpl extends SqlMapClientDaoSupport implements NotePersistence {

    private static final Logger logger = Logger.getLogger(NotePersistenceImpl.class);

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#getNotes(com.sec.ax.restful.pojo.Query)
	 */
	@Override
	public List<Note> getNotes(Query query) throws DataAccessException {

        logger.debug("..");

        Paging paging = query.getPaging();
        
    	@SuppressWarnings("unchecked")
        List<Note> list = getSqlMapClientTemplate().queryForList("note.getNotes", query, paging.getSkipResults(), paging.getMaxResults());
    	
		return list;
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#cntNote()
	 */
	@Override
	public int cntNote() throws DataAccessException {
		
        logger.debug("..");
		
        return (int) getSqlMapClientTemplate().queryForObject("note.cntNote");
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#getNote(int)
	 */
	@Override
	public Note getNote(int idx) throws DataAccessException {

        logger.debug("..");
        
        Note note= (Note) getSqlMapClientTemplate().queryForObject("note.getNote", idx);
        
		return note;
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#createNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public int createNote(Note note) throws DataAccessException {

        logger.debug("..");
        
		return (int) getSqlMapClientTemplate().insert("note.createNote", note);
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#updateNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public int updateNote(Note note) throws DataAccessException {
		
		logger.debug("..");
		
		return getSqlMapClientTemplate().update("note.updateNote", note);
		
	}
	
	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#deleteNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public int deleteNote(Note note) throws DataAccessException {
		
		logger.debug("..");
		
		return getSqlMapClientTemplate().delete("note.deleteNote", note);
		
	}
	
}