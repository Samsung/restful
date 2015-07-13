package com.samsung.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class List {
    
    private Query query;
    private Object object;
    
    public List() {        
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
}
