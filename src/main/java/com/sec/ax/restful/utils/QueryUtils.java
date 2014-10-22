package com.sec.ax.restful.utils;

import com.sec.ax.restful.pojo.Paging;
import com.sec.ax.restful.pojo.Query;

/**
 *
 * @author heesik.jeon
 *
 */

public class QueryUtils {
	
	/**
	 * @param pn
	 * @param search
	 * @return
	 */
	public static Query setQuery(int pn, String search) {
		
        Query query = new Query();
        
        Paging paging = new Paging();
        
        paging.setPn(pn);
        
        query.setSearch(search);
        query.setPaging(paging);
        
        return query;
        
	}
	
}
