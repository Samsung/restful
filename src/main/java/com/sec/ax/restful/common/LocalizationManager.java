package com.sec.ax.restful.common;

/**
 * 
 * @author heesik.jeon
 *
 */

public interface LocalizationManager {
	
    /**
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code, Object[] args);

}
