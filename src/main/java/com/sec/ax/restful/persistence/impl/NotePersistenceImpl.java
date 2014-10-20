package com.sec.ax.restful.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sec.ax.restful.persistence.NotePersistence;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Paging;

/**
 * 
 * @author heesik.jeon
 *
 */

public class NotePersistenceImpl extends SqlMapClientDaoSupport implements NotePersistence {

    private static final Logger logger = Logger.getLogger(NotePersistenceImpl.class);

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#getNotes(int)
	 */
	@Override
	public List<Note> getNotes(int pn) throws Exception {

        logger.debug("..");
        
        Paging paging = new Paging();
        
        paging.setPn(pn);

    	@SuppressWarnings("unchecked")
        List<Note> list = getSqlMapClientTemplate().queryForList("note.getNotes", paging.getPn(), paging.getMaxResults());
    	
		return list;
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#getNote(int)
	 */
	@Override
	public List<Note> getNote(int idx) throws Exception {

        logger.debug("..");
        
    	@SuppressWarnings("unchecked")
        List<Note> list = getSqlMapClientTemplate().queryForList("note.getNote", idx);
        
		return list;
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#createNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public Integer createNote(Note note) throws Exception {

        logger.debug("..");
        
		return (Integer) getSqlMapClientTemplate().insert("note.createNote", note);
		
	}

	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#updateNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public int updateNote(Note note) throws Exception {
		
		logger.debug("..");
		
		return getSqlMapClientTemplate().update("note.updateNote", note);
		
	}
	
	/* 
	 * @see com.sec.ax.restful.persistence.NotePersistence#deleteNote(com.sec.ax.restful.pojo.Note)
	 */
	@Override
	public int deleteNote(Note note) throws Exception {
		
		logger.debug("..");
		
		return getSqlMapClientTemplate().delete("note.deleteNote", note);
		
	}
	
}