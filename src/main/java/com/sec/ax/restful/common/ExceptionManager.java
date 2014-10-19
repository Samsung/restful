package com.sec.ax.restful.common;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface ExceptionManager {

    public void fireSystemException(Object request, Throwable cause);

}
