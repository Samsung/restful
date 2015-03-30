package com.sec.ax.restful.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.crypt.aes.AxCrypt;

/**
 * @author heesik.jeon
 *
 */

public class CryptDataSource extends BasicDataSource {
    
    @Autowired
    private ExceptionManager exceptionManager;

    /* 
     * @see org.apache.commons.dbcp.BasicDataSource#setPassword(java.lang.String)
     */
    @Override
    public synchronized void setPassword(String password) {
        
        try {
            super.setPassword(AxCrypt.decrypt("Vvb7oruYPkOHrEwoTVnXtw==", password));
        } catch (Exception e) {
            exceptionManager.fireSystemException(new Exception(e));
        }
        
    }

}
