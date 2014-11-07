package com.sec.ax.restful.common;

import java.util.List;

import com.sec.ax.restful.validate.Error;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface ExceptionManager {

    /**
     * @param e
     */
    public void fireSystemException(Exception e);
    
    /**
     * @param message
     * @param args
     */
    public void fireUserException(String message, Object[] args);

    /**
     * @param error
     */
    public void fireValidationException(List<Error> error);

}
