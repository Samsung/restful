package com.sec.ax.restful.pojo;

import java.util.Date;

/**
 * 
 * @author heesik.jeon
 *
 */

public abstract class Audit {
    
    private Date createdate;
    private Date updatedate;
    
    public Date getCreatedate() {
        return createdate;
    }
    
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    
    public Date getUpdatedate() {
        return updatedate;
    }
    
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

}
