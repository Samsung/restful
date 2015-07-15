package com.samsung.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class Query {
    
    private String search;
    private Paging paging;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    
    public static Query setQuery(int pn, String search) {
        
        Query query = new Query();
        
        Paging paging = new Paging();
        
        paging.setPn(pn);
        
        query.setSearch(search);
        query.setPaging(paging);
        
        return query;
        
    }
}
