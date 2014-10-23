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
     * @param cause
     */
    public void fireSystemException(Throwable cause);
    
    /**
     * @param request
     * @param cause
     */
    public void fireSystemException(Object request, Throwable cause);

    /**
     * @param error
     */
    public void fireValidationException(List<Error> error);

}
